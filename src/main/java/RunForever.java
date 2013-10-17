import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;


public class RunForever {
	static class SendEmailListener extends RunListener {
		@Override
		public void testFailure(Failure failure) throws Exception {
			System.out.println(failure.getTestHeader() + ", " + failure.getException() + ", " + failure.getMessage());
		}
	}

	public static void main(String[] argv) {
		while (true) {
			JUnitCore core = new JUnitCore();
			core.addListener(new SendEmailListener());
			core.run(PlaySongInChartTest.class);
		}
	}
}
