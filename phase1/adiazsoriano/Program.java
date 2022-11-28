public class Program {
    public static void main(String[] args) {
        
        String somelink = Something.protocol() +
                          Something.subdomain() +
                          Something.secondleveldomain() +
                          Something.topleveldomain() +
                          Something.userdirectory() +
                          Something.filename() +
                          Something.fileextension();
    }
}
