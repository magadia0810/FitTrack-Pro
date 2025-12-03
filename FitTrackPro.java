import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class FitTrackPro {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Workout> workouts = new ArrayList<>();
    private static int nextWorkoutId = 1;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    public static void main(String[] args) {
        printTitleBlock();
        scanner.nextLine();
        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": runNutritionCalculator(); break;
                case "2": addWorkout(); break;
                case "3": updateWorkoutStatus(); break;
                case "4": searchWorkoutByDate(); break;
                case "5": monitorWeeklyStatus(); break;
                case "6": deleteWorkout(); break;
                case "7": viewWorkoutHistory(); break;
                case "8":
                    clearScreen();
                    System.out.println("\nThank you for using FitTrack Pro! Stay consistent and strong!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice — please enter a number from 1 to 8.\n");
            }
        }
    }

    private static void printTitleBlock() {
        String green = "\033[32m";
        String reset = "\033[0m";
        System.out.println(green + "╔═════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                                         ║");
        System.out.println("║      ███████╗██╗████████╗    ████████╗██████╗  █████╗  ██████╗ ██╗  ██╗   ██████╗ ██████╗ ███████╗      ║");
        System.out.println("║      ██╔════╝██║╚══██╔══╝    ╚══██╔══╝██╔══██╗██╔══██╗██╔═══██╗██║ ██╔╝   ██╔══██╗██╔══██╗██╔═══██╗     ║");
        System.out.println("║      █████╗  ██║   ██║          ██║   ██████╔╝███████║██║      █████╔╝    ██████╔╝██████╔╝██║   ██║     ║");
        System.out.println("║      ██╔══╝  ██║   ██║          ██║   ██╔══██╗██╔══██║██║      ██╔═██╗    ██╔═══╝ ██╔══██╗██║   ██║     ║");
        System.out.println("║      ██║     ██║   ██║          ██║   ██║  ██║██║  ██║╚██████╔╝██║  ██╗   ██║     ██║  ██║╚██████╔╝     ║");
        System.out.println("║      ╚═╝     ╚═╝   ╚═╝          ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝   ╚═╝     ╚═╝  ╚═╝ ╚═════╝      ║");
        System.out.println("║                                                                                                         ║");
        System.out.println("║                                 FIT TRACK PRO - Console Fitness Tracker                                 ║");
        System.out.println("║                           Monitor | Calculate | Improve | Achieve Your Goals!                           ║");
        System.out.println("║                                                                                                         ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════════╝" + reset);
        System.out.println("\nPress ENTER to start...");
    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 20; i++) System.out.println();
        }
    }

    private static void printMainMenu() {
        clearScreen();
        String green = "\033[32m";
        String reset = "\033[0m";
        System.out.println(green + "════════════════════════════════════════════════════════════════════" + reset);
        System.out.println(green + "                     MAIN MENU - FIT TRACK PRO " + reset);
        System.out.println(green + "════════════════════════════════════════════════════════════════════" + reset);
        System.out.println("[1] Calorie & Nutrition Calculator");
        System.out.println("[2] Add Workout");
        System.out.println("[3] Update Workout Status");
        System.out.println("[4] Search Workout by Date");
        System.out.println("[5] Monitor Weekly Status");
        System.out.println("[6] Delete Workout");
        System.out.println("[7] View Workout History");
        System.out.println("[8] Exit");
        System.out.print("\nChoose an option: ");
    }

    private static void runNutritionCalculator() {
        clearScreen();
        String green = "\033[32m"; String reset = "\033[0m";

        System.out.println(green + "══════════════════════════════════════════════" + reset);
        System.out.println(green + "     CALORIE & NUTRITION CALCULATOR MENU" + reset);
        System.out.println(green + "══════════════════════════════════════════════" + reset);
        System.out.println("[1] Suggested Macronutrients");
        System.out.println("[2] Suggested Workout");
        System.out.print("\nChoose an option: ");
        String subChoice = scanner.nextLine().trim();

        if (subChoice.equals("2")) {
            clearScreen();
            System.out.println(green + "══════════════════════════════════════════════" + reset);
            System.out.println(green + "            SUGGESTED WORKOUT" + reset);
            System.out.println(green + "══════════════════════════════════════════════\n" + reset);

            System.out.print("Enter goal (lose/gain/maintain): ");
            String goal = scanner.nextLine().trim().toLowerCase();
            System.out.print("Enter intensity (high/average/low): ");
            String intensity = scanner.nextLine().trim().toLowerCase();

            System.out.println("\nBased on your goal and intensity, here is a suggested workout:");
            if (goal.equals("lose")) {
                if (intensity.equals("high")) {
                    System.out.println("- 30 min HIIT\n- 20 min brisk walk\n- 15 min jump rope\n- Light core training (planks, crunches)");
                } else if (intensity.equals("average")) {
                    System.out.println("- 40 min moderate jog\n- 15 min abs circuit\n- 20 min cycling\n- Bodyweight squats and lunges");
                } else {
                    System.out.println("- 30 min walk\n- 20 min yoga stretches\n- 10 min balance exercises (single-leg stands)");
                }
            } else if (goal.equals("gain")) {
                if (intensity.equals("high")) {
                    System.out.println("- 60 min weight training (push/pull split)\n- 15 min compound lifts (deadlift, bench, squat)\n- Protein recovery shake\n- 10 min mobility work");
                } else if (intensity.equals("average")) {
                    System.out.println("- 45 min full-body resistance training\n- 10 min cardio cooldown\n- 15 min arm/leg isolation exercises");
                } else {
                    System.out.println("- 30 min light weights\n- 15 min bodyweight strength (push-ups, squats)\n- Mobility and flexibility drills");
                }
            } else { // maintain
                if (intensity.equals("high")) {
                    System.out.println("- 45 min mixed cardio/strength\n- 20 min circuit training\n- Stretching and foam rolling");
                } else if (intensity.equals("average")) {
                    System.out.println("- 30 min strength training\n- 15 min brisk walk\n- 10 min core exercises");
                } else {
                    System.out.println("- 20 min walk\n- 15 min light yoga/meditation\n- Gentle stretching");
                }
            }
            System.out.println("\nPress Enter to return to Main Menu...");
            scanner.nextLine();
            return;
        }

        // Suggested Macro
        clearScreen();
        System.out.println(green + "══════════════════════════════════════════════" + reset);
        System.out.println(green + "          CALORIE & NUTRITION CALCULATOR" + reset);
        System.out.println(green + "══════════════════════════════════════════════\n" + reset);

        LocalDate date = readDate("Enter Date (YYYY-MM-DD): ");
        int age = readInt("Enter Age: ", 1, 120);
        System.out.print("Enter Sex (M/F): ");
        String sexInput = scanner.nextLine().trim().toUpperCase();
        Sex sex = sexInput.startsWith("F") ? Sex.FEMALE : Sex.MALE;

        double weightKg = readWeightKg();
        double heightCm = readHeightCm();

        System.out.println("Activity Level:\n 1. Sedentary - 1.2 (Little or No Exercise)\n 2. Moderately Active - 1.4(Exercise 1 to 3 times a week)\n 3. Very Active - 1.6(Exercise 4 to 5 times a week)\n 4. Extra Active - 1.9(Intense and Daily Exercise)");
        int actChoice = readInt("Choose activity level (1-4): ", 1, 4);
        double activityFactor = (actChoice == 1) ? 1.2 : (actChoice == 2) ? 1.4 : (actChoice == 3) ? 1.6 : 1.9;

        System.out.print("Enter goal (lose/gain/maintain): ");
        String goal = scanner.nextLine().trim().toLowerCase();
        if (!(goal.equals("lose") || goal.equals("gain") || goal.equals("maintain"))) goal = "maintain";

        NutritionResult res = NutritionCalculator.calculate(age, sex, weightKg, heightCm, activityFactor, goal);

        System.out.println("\n-- Daily Nutrition Recommendation --");
        System.out.printf("Date: %s\n", date.format(DATE_FORMAT));
        System.out.printf("BMR: %.0f kcal/day\n", res.bmr);
        System.out.printf("TDEE: %.0f kcal/day\n", res.tdee);
        System.out.printf("Suggested Calorie Intake: %.0f kcal/day\n", res.suggestedCalories);

        System.out.println(green + "\n══════════════════════════════════════════════════" + reset);
        System.out.println(green + "          DAILY MACRONUTRIENT BREAKDOWN" + reset);
        System.out.println(green + "══════════════════════════════════════════════════" + reset);
        System.out.printf("| %-15s | %-10s | %-10s |\n", "Nutrient", "Amount", "Unit");
        System.out.printf("| %-15s | %-10.0f | %-10s |\n", "Protein", res.proteinGrams, "g/day");
        System.out.printf("| %-15s | %-10.0f | %-10s |\n", "Carbs", res.carbsGrams, "g/day");
        System.out.printf("| %-15s | %-10.0f | %-10s |\n", "Fat", res.fatGrams, "g/day");

        System.out.println("\nPress Enter to return to Main Menu...");
        scanner.nextLine();
    }

    private static double readWeightKg() {
        System.out.println("Choose Weight Unit:\n 1. Pounds (lb)\n 2. Kilogram (kg)");
        int wChoice = readInt("Enter choice (1-2): ", 1, 2);
        if (wChoice == 1) {
            double lb = readDouble("Enter Weight (lb): ", 1, 1000);
            return lb * 0.45359237;
        } else {
            return readDouble("Enter Weight (kg): ", 1, 500);
        }
    }

    private static double readHeightCm() {
        System.out.println("Choose Height Unit:\n 1. Centimeter (cm)\n 2. Meter (m)");
        int hChoice = readInt("Enter choice (1-2): ", 1, 2);
        if (hChoice == 1) return readDouble("Enter Height (cm): ", 30, 300);
        double meters = readDouble("Enter Height (m): ", 0.5, 3);
        return meters * 100.0;
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < min || value > max) throw new NumberFormatException();
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
            }
        }
    }

    private static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value < min || value > max) throw new NumberFormatException();
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number between " + min + " and " + max + ".");
            }
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(scanner.nextLine().trim(), DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }

    // ======= WORKOUT MANAGEMENT METHODS =======
    private static void addWorkout() {
        clearScreen();
        System.out.println("Add a Workout");
        int week = readInt("Enter Week Number: ", 1, 52);
        int day = readInt("Enter Day Number: ", 1, 7);
        LocalDate date = readDate("Enter Date (YYYY-MM-DD): ");
        System.out.print("Enter Intensity (Low/Medium/High): ");
        String intensity = scanner.nextLine().trim();
        System.out.print("Enter Goal (Lose/Gain/Maintain): ");
        String goal = scanner.nextLine().trim();
        System.out.println("Workout Type:\n1. Cardio\n2. Strength\n3. Flexibility");
        int typeChoice = readInt("Choose type (1-3): ", 1, 3);

        Workout w;
        switch (typeChoice) {
            case 1: w = new Cardio(nextWorkoutId++, week, day, date, intensity, goal); break;
            case 2: w = new Strength(nextWorkoutId++, week, day, date, intensity, goal); break;
            default: w = new Flexibility(nextWorkoutId++, week, day, date, intensity, goal); break;
        }

        workouts.add(w);
        System.out.println("Workout added successfully! Press Enter to continue...");
        scanner.nextLine();
    }

    private static void updateWorkoutStatus() {
        clearScreen();
        if (workouts.isEmpty()) {
            System.out.println("No workouts to update. Press Enter to continue...");
            scanner.nextLine();
            return;
        }
        printWorkoutsTable(workouts);
        int id = readInt("Enter Workout ID to update: ", 1, nextWorkoutId - 1);
        workouts.stream().filter(w -> w.getId() == id).findFirst().ifPresentOrElse(w -> {
            System.out.println("Current Status: " + w.getStatus());
            System.out.println("1. Pending\n2. Completed");
            int s = readInt("Choose new status: ", 1, 2);
            w.setStatus(s == 1 ? WorkoutStatus.PENDING : WorkoutStatus.COMPLETED);
            System.out.println("Status updated! Press Enter to continue...");
            scanner.nextLine();
        }, () -> {
            System.out.println("Workout not found. Press Enter to continue...");
            scanner.nextLine();
        });
    }

    private static void searchWorkoutByDate() {
        clearScreen();
        if (workouts.isEmpty()) {
            System.out.println("No workouts recorded. Press Enter to continue...");
            scanner.nextLine();
            return;
        }
        LocalDate date = readDate("Enter date to search (YYYY-MM-DD): ");
        List<Workout> results = new ArrayList<>();
        for (Workout w : workouts) if (w.getDate().equals(date)) results.add(w);
        if (results.isEmpty()) System.out.println("No workouts found on " + date);
        else printWorkoutsTable(results);
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private static void monitorWeeklyStatus() {
        clearScreen();
        if (workouts.isEmpty()) {
            System.out.println("No workouts recorded. Press Enter to continue...");
            scanner.nextLine();
            return;
        }
        Map<Integer, Integer> completedCount = new HashMap<>();
        Map<Integer, Integer> totalCount = new HashMap<>();
        for (Workout w : workouts) {
            totalCount.put(w.getWeekNumber(), totalCount.getOrDefault(w.getWeekNumber(), 0) + 1);
            if (w.getStatus() == WorkoutStatus.COMPLETED)
                completedCount.put(w.getWeekNumber(), completedCount.getOrDefault(w.getWeekNumber(), 0) + 1);
        }
        System.out.println("Weekly Completion Status:");
        for (Integer week : totalCount.keySet()) {
            int completed = completedCount.getOrDefault(week, 0);
            int total = totalCount.get(week);
            System.out.printf("Week %d: %d/%d completed\n", week, completed, total);
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private static void deleteWorkout() {
        clearScreen();
        if (workouts.isEmpty()) {
            System.out.println("No workouts to delete. Press Enter to continue...");
            scanner.nextLine();
            return;
        }
        printWorkoutsTable(workouts);
        int id = readInt("Enter Workout ID to delete: ", 1, nextWorkoutId - 1);
        workouts.removeIf(w -> w.getId() == id);
        System.out.println("Workout deleted if it existed. Press Enter to continue...");
        scanner.nextLine();
    }

    private static void viewWorkoutHistory() {
        clearScreen();
        if (workouts.isEmpty()) {
            System.out.println("No workouts recorded. Press Enter to continue...");
            scanner.nextLine();
            return;
        }
        printWorkoutsTable(workouts);
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private static void printWorkoutsTable(List<Workout> list) {
        System.out.printf("| %-3s | %-5s | %-3s | %-10s | %-10s | %-10s | %-10s |\n",
                "ID", "Week", "Day", "Date", "Intensity", "Goal", "Status");
        System.out.println("-------------------------------------------------------------------------------");
        for (Workout w : list) {
            System.out.printf("| %-3d | %-5d | %-3d | %-10s | %-10s | %-10s | %-10s |\n",
                    w.getId(), w.getWeekNumber(), w.getDayNumber(),
                    w.getDate().format(DATE_FORMAT), w.getIntensity(), w.getGoal(), w.getStatus());
        }
    }

    // ======= NUTRITION SUPPORT CLASSES =======
    enum Sex { MALE, FEMALE }

    static class NutritionResult {
        double bmr, tdee, suggestedCalories, proteinGrams, carbsGrams, fatGrams;
        NutritionResult(double b, double t, double c, double p, double carb, double f) {
            bmr = b; tdee = t; suggestedCalories = c; proteinGrams = p; carbsGrams = carb; fatGrams = f;
        }
    }

    static class NutritionCalculator {
        static NutritionResult calculate(int age, Sex sex, double weightKg, double heightCm,
                                         double activityFactor, String goal) {
            double bmr;
            if (sex == Sex.MALE) bmr = 10*weightKg + 6.25*heightCm - 5*age + 5;
            else bmr = 10*weightKg + 6.25*heightCm - 5*age - 161;
            double tdee = bmr * activityFactor;

            double suggestedCalories = tdee;
            if (goal.equals("lose")) suggestedCalories -= 500;
            else if (goal.equals("gain")) suggestedCalories += 500;

            double proteinGrams = weightKg * 1.8;
            double fatGrams = weightKg * 0.8;
            double carbsGrams = (suggestedCalories - (proteinGrams*4 + fatGrams*9)) / 4;

            return new NutritionResult(bmr, tdee, suggestedCalories, proteinGrams, carbsGrams, fatGrams);
        }
    }

    // ======= WORKOUT SUPPORT CLASSES =======
    enum WorkoutStatus { PENDING, COMPLETED }

    static abstract class Workout {
        private final int id;
        private final int weekNumber;
        private final int dayNumber;
        private final LocalDate date;
        private final String intensity;
        private final String goal;
        private WorkoutStatus status = WorkoutStatus.PENDING;

        Workout(int id, int week, int day, LocalDate date, String intensity, String goal) {
            this.id = id; this.weekNumber = week; this.dayNumber = day;
            this.date = date; this.intensity = intensity; this.goal = goal;
        }

        public int getId() { return id; }
        public int getWeekNumber() { return weekNumber; }
        public int getDayNumber() { return dayNumber; }
        public LocalDate getDate() { return date; }
        public String getIntensity() { return intensity; }
        public String getGoal() { return goal; }
        public WorkoutStatus getStatus() { return status; }
        public void setStatus(WorkoutStatus s) { status = s; }
    }

    static class Cardio extends Workout { Cardio(int id,int w,int d,LocalDate dt,String i,String g){super(id,w,d,dt,i,g);} }
    static class Strength extends Workout { Strength(int id,int w,int d,LocalDate dt,String i,String g){super(id,w,d,dt,i,g);} }
    static class Flexibility extends Workout { Flexibility(int id,int w,int d,LocalDate dt,String i,String g){super(id,w,d,dt,i,g);} }
}

