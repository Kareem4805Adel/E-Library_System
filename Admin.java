public class Admin extends User {
    void addUser(String username, int age, int id, String email, String phoneNumber, String password, String role){
        User user = new User(username,age,id,email,phoneNumber,password,role);
    }
    void removeUser(){
        manageProfile(null,null,0);
    }
    void editUser(String username,String email,int id){
       User u =getUser(id);
        u.manageProfile(username,email,1);
    }

}
