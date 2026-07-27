package com.plog.plogbackend.domain.notice.controller;

import com.plog.plogbackend.domain.notice.controller.dto.NoticeListResponse;
import com.plog.plogbackend.domain.notice.controller.dto.NoticeRequest;
import com.plog.plogbackend.domain.notice.controller.dto.NoticeResponse;
import com.plog.plogbackend.domain.notice.service.NoticeService;
import com.plog.plogbackend.domain.notice.service.dto.NoticeResult;
import com.plog.plogbackend.domain.notice.service.dto.NoticesResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "공지사항")
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

  private final NoticeService noticeService;

  @Operation(summary = "전체 조회")
  @GetMapping
  public ResponseEntity<List<NoticeListResponse>> noticeList(NoticeRequest request) {

    List<NoticesResult> noticeResults = noticeService.noticeResultList(request);

    List<NoticeListResponse> responses =
        noticeResults.stream().map(NoticeListResponse::of).toList();

    return ResponseEntity.ok(responses);
  }

  @Operation(summary = "상세 조회")
  @GetMapping("/{id}")
  public ResponseEntity<NoticeResponse> notice(@PathVariable Long id) {

    NoticeResult noticeResult = noticeService.findNotice(id);

    NoticeResponse response = NoticeResponse.of(noticeResult);

    return ResponseEntity.ok(response);
  }
}
