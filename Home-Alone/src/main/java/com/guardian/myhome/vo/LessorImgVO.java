package com.guardian.myhome.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessorImgVO {
   
   private String lessorId;
   
   private String fileName;
   
   private String uploadPath;
   
   private String uuid;

}