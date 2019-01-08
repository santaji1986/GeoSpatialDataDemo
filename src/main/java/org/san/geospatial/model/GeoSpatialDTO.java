package org.san.geospatial.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeoSpatialDTO {
	private int utmZone;
	private String latitudeBand;
	private String gridSquare;
	private LocalDate date;
	private String channelMap;
}
