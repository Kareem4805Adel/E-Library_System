import java.io.*;
import java.util.*;
public class User {
    private String username;
    private int age;
    private int id;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User(String username, int age, int id, String email, String phoneNumber, String password, String role) {
        this.username = username;
        this.age = age;
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role= role;
        try (FileWriter writer = new FileWriter("Accounts.CSV",true)) {
            writer.write("\n"+
                        this.username + "," +
                                this.age + "," +
                                this.id+ "," +
                                this.email + "," +
                                this.phoneNumber + "," +
                                this.password+","+
                                this.role+ "\n");



        } catch (IOException e) {
            e.printStackTrace();
        }


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
        if(password.length()<4){
            return false;
        }
        this.password = password;
        return true;
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

    public static User getUser(int id){
        try (BufferedReader br = new BufferedReader(new FileReader("Accounts.CSV"))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if(id==(Integer.parseInt(data[2]))){
                    User user = new User(data[0],Integer.parseInt(data[1]),id,data[3],data[4],data[5],data[6]);
                    return user;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean login(String Username,String password){
        try (BufferedReader br = new BufferedReader(new FileReader("Accounts.CSV"))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if(Username.equalsIgnoreCase(data[0])&& password.equals(data[5])){
                    return true;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
         return false;
    }

    public void manageProfile(String Username,String email,int op){

        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Accounts.CSV"))) {
            String line;

            // Skip header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                User u = new User();
                u.setUsername(data[0]);
                System.out.println(data[0]);
                u.setAge(Integer.parseInt(data[1]));
                u.setId(Integer.parseInt(data[2]));
                u.setEmail(data[3]);
                u.setPhoneNumber(data[4]);
                u.setPassword(data[5]);
                u.setRole(data[6]);
                users.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(op==0){
            for(int i=0;i<users.size();i++)
            if(users.get(i).getId()==this.id){
                users.remove(i);
                break;
            }
        }
        else{
            for(int i=0;i<users.size();i++)
                if(users.get(i).getId()==this.id){
                    users.get(i).setUsername(Username);
                    users.get(i).setEmail(email);
                    break;
                }
        }
        try (FileWriter writer = new FileWriter("Accounts.CSV")) {

            writer.write("username,age,id,email,phoneNumber,password,role\n");

            for (User u : users) {
                writer.write(
                        u.getUsername() + "," +
                                u.getAge() + "," +
                               u.getId()+ "," +
                                u.getEmail() + "," +
                                u.getPhoneNumber() + "," +
                                u.getPassword()+","+
                                u.getRole()+ "\n"
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    };


}
