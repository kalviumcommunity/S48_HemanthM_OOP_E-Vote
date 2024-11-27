import java.util.*;

abstract class Person {
    protected String name;

    public Person() {
        this.name = "Unknown";
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void castVote(String candidate, String party);
}

interface VoterActions { // <- Added interface for Voter actions
    void castVote(String candidate, String party); // <- Method for casting a vote
    boolean hasVoted(); // <- Method to check if voter has voted
}

interface CandidateActions { // <- Added interface for Candidate actions
    void receiveVote(); // <- Method to receive a vote
    void displayPartyVotes(); // <- Method to display party votes
}

class Voter extends Person implements VoterActions { // <- Implementing VoterActions interface
    private String voterId;
    private static int totalVoters = 0;
    private boolean hasVoted;

    public Voter(String name, String voterId) {
        super(name);
        this.voterId = voterId;
        this.hasVoted = false;
        totalVoters++;
    }

    public String getVoterId() {
        return voterId;
    }

    public boolean hasVoted() { // <- Implemented hasVoted method from VoterActions
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public static int getTotalVoters() {
        return totalVoters;
    }

    @Override
    public void castVote(String candidate, String party) { // <- Implemented castVote method from VoterActions
        System.out.println("Voter " + voterId + " (" + name + ") voted for " + candidate + " (" + party + ")");
    }
}

class Candidate extends Person implements CandidateActions { // <- Implementing CandidateActions interface
    private String party;
    private int voteCount;

    public Candidate(String name, String party) {
        super(name);
        this.party = party;
        this.voteCount = 0;
    }

    public String getParty() {
        return party;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void receiveVote() { // <- Implemented receiveVote method from CandidateActions
        voteCount++;
    }

    public void displayPartyVotes() { // <- Implemented displayPartyVotes method from CandidateActions
        System.out.println(party + " Party Votes: " + voteCount);
    }

    @Override
    public void castVote(String candidate, String party) {
        System.out.println("Candidates cannot vote.");
    }
}

public class mainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Voter[] voters = {
                new Voter("John", "V001"),
                new Voter("Alice", "V002"),
                new Voter("Ravi", "V003"),
                new Voter("Sita", "V004"),
                new Voter("Mohan", "V005")
        };

        Candidate[] candidates = {
                new Candidate("Bhagat", "Red Party"),
                new Candidate("Azad", "Green Party")
        };

        String password = "12345678";
        int votersVoted = 0;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Cast Vote");
            System.out.println("2. Show Details");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Cast Vote
                    System.out.println("Enter your name:");
                    String inputName = scanner.nextLine();
                    System.out.println("Enter your Voter ID:");
                    String inputVoterId = scanner.nextLine();

                    boolean voterFound = false;
                    for (Voter voter : voters) {
                        if (voter.getName().equals(inputName) && voter.getVoterId().equals(inputVoterId)) {
                            voterFound = true;
                            if (voter.hasVoted()) {
                                System.out.println("You have already voted.");
                            } else {
                                System.out.println("Choose a candidate to vote:");
                                for (int i = 0; i < candidates.length; i++) {
                                    System.out.println((i + 1) + ": " + candidates[i].getName() + " (" + candidates[i].getParty() + ")");
                                }

                                int candidateChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (candidateChoice >= 1 && candidateChoice <= candidates.length) {
                                    voter.castVote(candidates[candidateChoice - 1].getName(), candidates[candidateChoice - 1].getParty());
                                    candidates[candidateChoice - 1].receiveVote();
                                    voter.setHasVoted(true);
                                    votersVoted++;
                                } else {
                                    System.out.println("Invalid candidate choice.");
                                }
                            }
                            break;
                        }
                    }

                    if (!voterFound) {
                        System.out.println("Voter not found.");
                    }
                    break;

                case 2: // Show Details
                    System.out.println("Enter password:");
                    String enteredPassword = scanner.nextLine();
                    if (enteredPassword.equals(password)) {
                        System.out.println("Total Voters: " + Voter.getTotalVoters());
                        System.out.println("Total Voters Who Voted: " + votersVoted);
                        for (Candidate candidate : candidates) {
                            candidate.displayPartyVotes();
                        }
                    } else {
                        System.out.println("Incorrect password.");
                    }
                    break;

                case 3: // Exit
                    System.out.println("Enter password:");
                    String firstPassword = scanner.nextLine();
                    System.out.println("Confirm password:");
                    String confirmPassword = scanner.nextLine();

                    if (firstPassword.equals(password) && confirmPassword.equals(password)) {
                        Candidate winner = null;
                        int maxVotes = -1;
                        int secondMaxVotes = -1;

                        for (Candidate candidate : candidates) {
                            if (candidate.getVoteCount() > maxVotes) {
                                secondMaxVotes = maxVotes;
                                maxVotes = candidate.getVoteCount();
                                winner = candidate;
                            } else if (candidate.getVoteCount() > secondMaxVotes) {
                                secondMaxVotes = candidate.getVoteCount();
                            }
                        }

                        int lead = maxVotes - secondMaxVotes;
                        if (winner != null) {
                            System.out.println("Winner: " + winner.getName() + " (" + winner.getParty() + ")");
                            System.out.println("Vote Lead: " + lead);
                        }
                        return;
                    } else {
                        System.out.println("Passwords do not match. Returning to menu.");
                    }
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
