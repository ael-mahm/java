class Program {
        public static void main(String[] args) {
                if (args.length > 1) {
                        System.out.println("Error [Number of args!]");
                        System.exit(-1);
                }

                boolean devMode = args.length >= 1 && args[0].equals("--profile=dev");
                Menu menu = new Menu(devMode);
                menu.run();
        }
}
