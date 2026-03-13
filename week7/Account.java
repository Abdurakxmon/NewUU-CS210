class Account {
    private String id;
    private String password;
    private AccountStatus status;

    public Account(String id, String password) {
        this.id = id;
        this.password = password;
        this.status = AccountStatus.ACTIVE;
    }

    public boolean resetPassword() {
        this.password = "230248MA";
        System.out.println("Password reset for account: " + id);
        return true;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public AccountStatus getStatus() { return status; }
    public void setStatus(AccountStatus status) { this.status = status; }
}