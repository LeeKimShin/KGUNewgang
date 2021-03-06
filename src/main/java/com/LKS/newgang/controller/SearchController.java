package com.LKS.newgang.controller;

import com.LKS.newgang.service.SearchService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    /**
     * 전공이나 학과에 따라서 강의 들을 전송
     * @param info campus, colleague, department, major 정보가 들어있음
     * @return 강의 정보들
     */
    @PostMapping("/search/lectures")
    @PreAuthorize(value = "hasAuthority('course:read')")
    public ResponseEntity<?> getLectureList(@RequestBody HashMap<String, String> info) {
        String campus = info.get("campus");
        String colleague = info.get("colleague");
        String department = info.get("department");
        String major = info.get("major");

        try {
            if (Strings.isNullOrEmpty(major)) {
                return ResponseEntity.ok(searchService.findByDepartment(campus, colleague, department));
            } else {
                return ResponseEntity.ok(searchService.findByMajor(campus, colleague, department, major));
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/search/elective")
    @PreAuthorize(value = "hasAuthority('course:read')")
    public ResponseEntity<?> getElective(@RequestBody HashMap<String, String> info){
        String campus = info.get("campus");
        String classification = info.get("classification");

        try {
            return ResponseEntity.ok(searchService.getElectiveList(campus, classification));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 로그인한 학생의 소속 정보 전송
     * @param authentication 로그인한 학생의 정보
     * @return 학생의 소속 정보
     */
    @PostMapping("/search/stdBelong")
    @PreAuthorize(value = "hasAuthority('student:read')")
    public ResponseEntity<?> getStdBelong(Authentication authentication) {
        Optional<HashMap<String, String>> stdInfo = searchService.stdBelonging(authentication.getName());

        final ResponseEntity<?>[] result = new ResponseEntity<?>[1];

        stdInfo.ifPresentOrElse(info -> {
            result[0] = ResponseEntity.ok(info);
        }, () -> result[0] = ResponseEntity.badRequest().build());

        return result[0];
    }

    /**
     * 선택된 캠퍼스를 기반으로 소속된 대학 정보 전송
     * @param info DB 검색에 필요한 정보
     * @return 대학 정보
     */
    @PostMapping("/colleagueList")
    @PreAuthorize(value = "hasAuthority('course:read')")
    public ResponseEntity<?> getColleagueList(@RequestBody HashMap<String,String> info){
        try {
            return ResponseEntity.ok(searchService.colleagueList(info));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 선택된 캠퍼스, 대학, 학과를 기반으로 해당되는 전공 정보 전송
     * @param info DB 검색에 필요한 정보
     * @return 전공 정보
     */
    @PostMapping("/majorList")
    @PreAuthorize(value = "hasAuthority('course:read')")
    public ResponseEntity<?> getMajorList(@RequestBody HashMap<String,String> info){
        try {
            return ResponseEntity.ok(searchService.majorList(info));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 선택된 캠퍼스, 대학을 기반으로 해당되는 학과 정보를 전송
     * @param info DB 검색에 필요한 정보
     * @return 학과 정보
     */
    @PostMapping("/departmentList")
    @PreAuthorize(value = "hasAuthority('course:read')")
    public ResponseEntity<?> getDeptList(@RequestBody HashMap<String,String> info) {
        try {
            return ResponseEntity.ok(searchService.deptList(info));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}