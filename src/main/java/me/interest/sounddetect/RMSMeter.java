package me.interest.sounddetect;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

public class RMSMeter {
	List<RMSListener> rmsListeners;
	CaptureThread captureThread;

	public RMSMeter(Mixer.Info mixerInfo, AudioFormat audioFormat) {
		this.rmsListeners = new ArrayList<RMSListener>();
		try {
			this.captureThread = new CaptureThread(mixerInfo, audioFormat);
		} catch (LineUnavailableException e) {
			throw new RuntimeException(e);
		}
	}

	public void start() {
		this.captureThread.start();
	}

	public void stop() {
		this.captureThread.setStop();
		try {
			this.captureThread.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void addRMSListener(RMSListener rmsListener) {
		this.rmsListeners.add(rmsListener);
	}

	public boolean removeRMSListener(RMSListener l) {
		return this.rmsListeners.remove(l);
	}

	class CaptureThread extends Thread {
		byte tempBuffer[] = new byte[1000];
		private TargetDataLine targetDataLine;
		private double mRmsSmoothed;
		private double mAlpha = 0.9;
		private boolean stopCapture;

		CaptureThread(Mixer.Info mixerInfo, AudioFormat audioFormat) throws LineUnavailableException {
			Mixer mixer = AudioSystem.getMixer(mixerInfo);
			DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
			targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);

			targetDataLine.open(audioFormat);
			targetDataLine.start();
		}

		public void setStop() {
			this.stopCapture = true;
		}

		public void run() {
			stopCapture = false;
			try {
				while (!stopCapture) {
					int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
					if (cnt > 0) {
						recomputeRmsSmoothed(tempBuffer, 0, tempBuffer.length);
						for (RMSListener l : rmsListeners) {
							l.processRMSSmoothed(getRMSSmoothed());
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e);
				System.exit(0);
			} finally {
				targetDataLine.stop();
				targetDataLine.close();
			}
		}

		public double getRMSSmoothed() {
			return mRmsSmoothed;
		}

		private double recomputeRmsSmoothed(byte[] tempBuffer, int start, int length) {
			double rms = 0;
			for (int i = 0; i < length; i++) {
				rms += tempBuffer[i] * tempBuffer[i];
			}

			rms = Math.sqrt(rms / length);
			mRmsSmoothed = (mRmsSmoothed * mAlpha) + (1 - mAlpha) * rms;
			return mRmsSmoothed;
		}
	}

	public boolean measureWhile(int duration, float threshold) {
		this.start();
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
		}
		this.stop();

		return this.captureThread.getRMSSmoothed() > threshold;
	}
}
