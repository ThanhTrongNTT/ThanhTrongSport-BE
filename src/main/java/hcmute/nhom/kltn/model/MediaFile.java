package hcmute.nhom.kltn.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * Class MediaFile.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Entity
@Table(name = "t_media_file")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaFile extends AbstractAuditModel {
    /**
     * MediaFile entity.
     */

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "removal_flag", nullable = false, length = 1)
    private Boolean removalFlag = false;

    @Override
    public String toString() {
        return "MediaFile [id=" + id
                + ", fileName=" + fileName
                + ", fileType=" + fileType
                + ", url=" + url
                + ", removalFlag=" + removalFlag + "]";
    }
}
