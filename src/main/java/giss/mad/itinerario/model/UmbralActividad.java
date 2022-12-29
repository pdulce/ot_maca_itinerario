package giss.mad.marcocalidad.itinerariocalidad.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "UmbralActividad", schema = "itinerario")
public class UmbralActividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "lower_limit", nullable = false)
    private Integer lowerLimit;

    @Column(name = "upper_limit", nullable = false)
    private Integer upperLimit;

    @Column(name = "activity_id", nullable = false)
    private Integer activityId;

    @Column(name = "element_type_id", nullable = false)
    private Integer elemenTypeId;

    @Column(name = "threshold", nullable = false)
    private String threshold;

    @Column(name = "help", nullable = false)
    private String help;

    @Column(name = "is_for_delivery")
    private boolean forDelivery;

    @Column(name = "exclude_unreached_threshold")
    private boolean excludeUnreachedThreshold;

    @Column(name = "is_deleted")
    private Integer deleted;

    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;

    @Column(name = "update_date")
    private Timestamp updateDate;


    public UmbralActividad() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Integer lower_limit) {
        this.lowerLimit = lower_limit;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upper_limit) {
        this.upperLimit = upper_limit;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activity_id) {
        this.activityId = activity_id;
    }

    public Integer getElemenTypeId() {
        return elemenTypeId;
    }

    public void setElemenTypeId(Integer element_type_id) {
        this.elemenTypeId = element_type_id;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public boolean isForDelivery() {
        return forDelivery;
    }

    public void setForDelivery(boolean is_for_delivery) {
        this.forDelivery = is_for_delivery;
    }

    public boolean isExcludeUnreachedThreshold() {
        return excludeUnreachedThreshold;
    }

    public void setExcludeUnreachedThreshold(boolean exclude_unreached_threshold) {
        this.excludeUnreachedThreshold = exclude_unreached_threshold;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer is_deleted) {
        this.deleted = is_deleted;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creation_date) {
        this.creationDate = creation_date;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp update_date) {
        this.updateDate = update_date;
    }
}
