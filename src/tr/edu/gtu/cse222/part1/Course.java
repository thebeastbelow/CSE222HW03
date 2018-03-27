package tr.edu.gtu.cse222.part1;

/**
 * Represents a course in the {@code CourseStructure}. Holds all information
 * about a course given in the csv file. Consists of just a bunch of setters
 * and getters.
 *
 * @author Enes Gonultas
 * @see CourseStructure
 */
public class Course {
    private int semester;
    private String code;
    private String title;
    private int ECTSCredits;
    private int GTUCredits;
    private String htl;

    public Course(int semester, String code, String title, int ECTSCredits,
           int GTUCredits, String htl) {
        this.semester = semester;
        this.code = code;
        this.title = title;
        this.ECTSCredits = ECTSCredits;
        this.GTUCredits = GTUCredits;
        this.htl = htl;
    }

    /**
     * Creates a String that contains all the information about course, in
     * a human readable format.
     *
     * @return String representing the course
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Semester: " + getSemester() + "\n");
        sb.append("Code: " + getCode() + "\n");
        sb.append("Title: " + getTitle() + "\n");
        sb.append("ECTS Credits: " + getECTSCredits() + "\n");
        sb.append("GTU Credits: " + getGTUCredits() + "\n");
        sb.append("H+T+L: " + getHtl() + "\n");

        return sb.toString();
    }

    /**
     * Gets semester.
     *
     * @return semester of the course
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Sets semester.
     *
     * @param semester new semester value
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * Gets code.
     *
     * @return code of the course
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code new code value
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets title.
     *
     * @return title of the course
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title new title value
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets ECTS credits.
     *
     * @return ECTS credits value of the course
     */
    public int getECTSCredits() {
        return ECTSCredits;
    }

    /**
     * Sets ECTS credits.
     *
     * @param ECTSCredits new ECTS credits value
     */
    public void setECTSCredits(int ECTSCredits) {
        this.ECTSCredits = ECTSCredits;
    }

    /**
     * Gets GTU credits.
     *
     * @return GTU credits value of the course
     */
    public int getGTUCredits() {
        return GTUCredits;
    }

    /**
     * Sets GTU credits.
     *
     * @param GTUCredits new GTU credits value
     */
    public void setGTUCredits(int GTUCredits) {
        this.GTUCredits = GTUCredits;
    }

    /**
     * Gets HTL value.
     *
     * @return HTL value of the course
     */
    public String getHtl() {
        return htl;
    }

    /**
     * Sets HTL value.
     *
     * @param htl new HTL value
     */
    public void setHtl(String htl) {
        this.htl = htl;
    }
}
