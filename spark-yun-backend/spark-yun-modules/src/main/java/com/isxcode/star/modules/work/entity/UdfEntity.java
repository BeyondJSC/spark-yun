package com.isxcode.star.modules.work.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.isxcode.star.common.config.CommonConfig.TENANT_ID;

@Data
@Entity
@SQLDelete(sql = "UPDATE SY_WORK_UDF SET deleted = 1 WHERE id = ? and version_number = ?")
@Where(clause = "deleted = 0 ${TENANT_FILTER} ")
@Table(name = "SY_WORK_UDF")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@EntityListeners(AuditingEntityListener.class)
public class UdfEntity {

	@Id
	@GeneratedValue(generator = "sy-id-generator")
	@GenericGenerator(name = "sy-id-generator", strategy = "com.isxcode.star.config.GeneratedValueConfig")
	private String id;

	private String type;

	private String fileId;

	private String funcName;

	private String className;

	private String resultType;

	private Boolean status;

	@CreatedDate
	private LocalDateTime createDateTime;

	@LastModifiedDate
	private LocalDateTime lastModifiedDateTime;

	@CreatedBy
	private String createBy;

	@LastModifiedBy
	private String lastModifiedBy;

	@Version
	private Long versionNumber;

	@Transient
	private Integer deleted;

	private String tenantId;

	@PrePersist
	public void prePersist() {
		this.tenantId = TENANT_ID.get();
	}
}