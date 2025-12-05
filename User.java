public abstract class User {
    private String username;
    private int age;
    private int id;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;

    public User(String username, int age, int id, String email, String phoneNumber, String password, String role) {
        this.username = username;
        this.age = age;
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role= role;
    }

    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {

        this.username = username;

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        boolean found = false;
        if(password.length()<8){
            return found;
        }
        found=true;
        this.password = password;
        return found;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser(){
        return this;
    }

    public void logout(){

    }

    public static void login(String Username,String password){

    }

    public abstract void manageProfile();


}
