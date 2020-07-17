package tuacy.microservice.framweork.bootqueue.rabbitmq.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@Data
@ToString
public class SysUser implements Serializable {
  private static final long serialVersionUID = 1L;

  private String id;

  private String userName;

  private String phone;

  private String name;

  private String password;

  private Date createDate;

  private Date updateDate;

  private Boolean delete;

  private String permissionIds;
}