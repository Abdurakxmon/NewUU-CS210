void main(String[] args) {
    try {
        FileLogger.writeLog("log.txt", "Test 123");
    } catch (RuntimeException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
