public class Test {

    public static void main(String[] args) {
        Administrator p1 = new Administrator("Gurjot", "GurjotG123", "Gurjot",
                new Address("24", "Brampton", "Canada", "L7A3S9"), AccountType.ADMINSTRATOR);
        System.out.println(p1.getUsername());
    }

}
