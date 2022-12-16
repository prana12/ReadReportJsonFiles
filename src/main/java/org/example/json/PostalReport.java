package org.example.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
//@JsonRootName(value = "postalReport")
public class PostalReport {
    @JsonProperty("datetime")
    private LocalDateTime datetime;
    @JsonProperty("batchStatus")
    private String batchStatus;
    @JsonProperty("successFileCount")
    private Integer successFileCount;
    @JsonProperty("failedFileCount")
    private Integer failedFileCount;
    @JsonProperty("rejectedFileCount")
    private Integer rejectedFileCount;
    //private FailedReportFiles failed;
    //private RejectedReportFiles rejected;
}
