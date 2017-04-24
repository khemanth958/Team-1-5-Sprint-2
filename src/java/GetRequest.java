/**
 *
 * @author Viranchi
 */
public class GetRequest 
{
    private String post_ID;
    private String user_ID;
    private String role;

    public String getPost_ID() {
        return post_ID;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public String getRole() {
        return role;
    }

    public void setPost_ID(String post_ID) {
        this.post_ID = post_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public GetRequest(String post_ID, String user_ID, String role) {
        this.post_ID = post_ID;
        this.user_ID = user_ID;
        this.role = role;
    }

    public GetRequest() {
    }
    
    
}
