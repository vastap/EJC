package ru.github.vastap;

/**
 * All inner classes are part (are members) of outer class.
 * So, outer class can see all private members and private members of private members.
 */
public class ViewScope {
	private final static String staticField = "Outer static secret";
	private final String memberField = "Outer member secret";

	public static void main(String[] args) {
		System.out.println("Outer class can see " + TopSecret.password);
		TopSecretStatic.login();
		//
		ViewScope vs = new ViewScope();
		TopSecret secret = vs.new TopSecret();
		System.out.println("Outer class can see " + secret.password);
		secret.login();
	}

	private static class TopSecretStatic {
		private static final String password = "Inner static secret";

		private static void login() {
			// Can see private static fields of parent
			System.out.println("Inner static class can see " + ViewScope.staticField);
		}
	}

	private class TopSecret {
		private static final String password = "Inner member secret";

		private void login() {
			// Can see private static and non-static fields of parent
			System.out.println("Inner class can see " + ViewScope.staticField);
			System.out.println("Inner class can see " + ViewScope.this.memberField);
		}
	}
}
