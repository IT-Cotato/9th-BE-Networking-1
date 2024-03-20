package com.cotato.networking1.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelManageService {

  void upload(MultipartFile file);
}
