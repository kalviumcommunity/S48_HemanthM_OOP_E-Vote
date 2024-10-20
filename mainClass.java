import java.util.*;

// Voter class
class Voter {

    // Private fields
    private String name;
    private String voterId;
    private static int totalVoters = 0;

    Voter(String name, String voterId) {
        this.name = name;
        this.voterId = voterId;
        totalVoters++;
    }

    public String getName() { // Public accessor
        return name;
    }

    public void setName(String name) { // Public mutator
        this.name = name;
    }

    public String getVoterId() { // Public accessor
        return voterId;
    }

    public void setVoterId(String voterId) {  // Public mutator
        this.voterId = voterId;
    }

    public static int getTotalVoters() {
        return totalVoters;
    }

    void castVote(String candidate, String party) {
        System.out.println("Voter ID: " + voterId + ", " + name + " voted for " + candidate + " of " + party);
    }
}

// Candidate class
class Candidate {

    // Private fields
    private String name;
    private String party;
    private int voteCount;
    private static int totalVotesCast = 0;

    Candidate(String name, String party) {
        this.name = name;
        this.party = party;
        this.voteCount = 0;
    }

    public String getName() { // Public accessor
        return name;
    }

    public void setName(String name) { // Public mutator
        this.name = name;
    }

    public String getParty() { // Public accessor
        return party;
    }

    public void setParty(String party) { // Public mutator
        this.party = party;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public static int getTotalVotesCast() {
        return totalVotesCast;
    }

    void receiveVote() {
        voteCount++;
        totalVotesCast++;
    }

    public static void displayTotalVotesCast() {
        System.out.println("Total Votes Cast in Election: " + totalVotesCast);
    }

    void displayPartyVotes() {
        System.out.println(party + " Party Votes: " + voteCount);
    }
}

public class mainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int votersVoted = 0;

        Voter[] voters = new Voter[15];
        voters[0] = new Voter("John", "V001");
        voters[1] = new Voter("Bob", "V002");
        voters[2] = new Voter("Alice", "V003");
        voters[3] = new Voter("Ravi", "V004");
        voters[4] = new Voter("Sita", "V005");
        voters[5] = new Voter("Mohan", "V006");
        voters[6] = new Voter("Radha", "V007");
        voters[7] = new Voter("Suresh", "V008");
        voters[8] = new Voter("Geeta", "V009");
        voters[9] = new Voter("Vikram", "V010");
        voters[10] = new Voter("Anjali", "V011");
        voters[11] = new Voter("Karan", "V012");
        voters[12] = new Voter("Deepa", "V013");
        voters[13] = new Voter("Ramesh", "V014");
        voters[14] = new Voter("Sunita", "V015");

        Candidate[] candidates = new Candidate[2];
        candidates[0] = new Candidate("Bhagat", "Red Party");
        candidates[1] = new Candidate("Azad", "Green Party");

        while (true) {
            boolean voterFound = false;

            while (!voterFound) {
                System.out.println("Enter your name:");
                String inputName = scanner.nextLine();
                System.out.println("Enter your Voter ID:");
                String inputVoterId = scanner.nextLine();

                for (Voter voter : voters) {
                    if (voter.getName().equals(inputName) && voter.getVoterId().equals(inputVoterId)) {
                        voterFound = true;
                        System.out.println("Voter verified.");

                        boolean validVote = false;
                        while (!validVote) {
                            System.out.println("Enter candidate's unique number to vote:");
                            for (int i = 0; i < candidates.length; i++) {
                                System.out.println((i + 1) + ": " + candidates[i].getName() + " (" + candidates[i].getParty() + ")");
                            }

                            int candidateChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (candidateChoice >= 1 && candidateChoice <= candidates.length) {
                                voter.castVote(candidates[candidateChoice - 1].getName(), candidates[candidateChoice - 1].getParty());
                                candidates[candidateChoice - 1].receiveVote();
                                votersVoted++;
                                validVote = true;
                            } else {
                                System.out.println("Invalid candidate choice. Please try again.");
                            }
                        }

                        boolean continueOptions = true;
                        while (continueOptions) {
                            System.out.println("\nWhat would you like to do next?");
                            System.out.println("1. Exit");
                            System.out.println("2. Next Vote");
                            System.out.println("3. Show Total Voters in Array");
                            System.out.println("4. Show Total Voters Who Voted");
                            System.out.println("5. Show Total Votes Received by Each Party");

                            int choice = scanner.nextInt();
                            scanner.nextLine();

                            switch (choice) {
                                case 1:
                                    System.out.println("Exiting program...");
                                    scanner.close();
                                    return;
                                case 2:
                                    continueOptions = false;
                                    break;
                                case 3:
                                    System.out.println("Total Voters in Array: " + Voter.getTotalVoters());
                                    break;
                                case 4:
                                    System.out.println("Total Voters Who Voted: " + votersVoted);
                                    break;
                                case 5:
                                    for (Candidate candidate : candidates) {
                                        candidate.displayPartyVotes();
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        }

                        break;
                    }
                }

                if (!voterFound) {
                    System.out.println("Voter not found or incorrect details provided. Please try again.");
                }
            }
        }
    }
}
