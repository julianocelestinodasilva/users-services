package julianocelestino.usersservices;

import javax.persistence.*;

/**
 * Created by juliano on 26/12/17.
 */

@Entity
public class Complain {

    public static final String MSG_INVALID = "Complain attributes (title,description,company) must not be null or empty";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String locale;

    @Column(nullable = false)
    private String company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean valid() {
        return (title!=null && !title.isEmpty())
                && (description!=null && !description.isEmpty())
                && (locale!=null && !locale.isEmpty())
                && (company!=null && !company.isEmpty());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Complain complain = (Complain) o;

        if (!title.equals(complain.title)) return false;
        if (!description.equals(complain.description)) return false;
        if (!locale.equals(complain.locale)) return false;
        return company.equals(complain.company);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + locale.hashCode();
        result = 31 * result + company.hashCode();
        return result;
    }

    public Complain(String title, String description, String company) {
        this.title = title;
        this.description = description;
        this.company = company;
    }

    public Complain() {
    }
}
