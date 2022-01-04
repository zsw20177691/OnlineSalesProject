package com.Important.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@TableName(value = "upload_file")
@ApiModel(value = "文件附件对象")
@Builder
public class FileEntity implements Serializable {

    private static final long serialVersionUID = -891515375273761364L;

    @ApiModelProperty(value = "文件表主键")
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String  id;


    @ApiModelProperty(value = "真实文件名")
    @TableField(value = "name")
    private String  name;


    @ApiModelProperty(value = "真实文件存储路径")
    @TableField(value = "real_path")
    private String  realPath;

    @ApiModelProperty(value = "网络路径")
    @TableField(value = "url")
    private String  url;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_date")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "ftp文件名")
    @TableField(value = "ftp_name")
    private String ftpName;

}
