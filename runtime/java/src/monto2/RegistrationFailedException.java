package monto2;

@SuppressWarnings("serial")
public class RegistrationFailedException extends RuntimeException {
	public RegisterServiceResponse response;

	public RegistrationFailedException(RegisterServiceResponse response) {
		this.response = response;
	}

	public String toString() {
		return String.format("Registration failed: %s", this.response);
	}
}
