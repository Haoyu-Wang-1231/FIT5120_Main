package fit5120.monash.edu.client;

import fit5120.monash.edu.features.searchingService.dto.clientRequest.GoogleMapNearbyRequest;
import fit5120.monash.edu.features.searchingService.dto.clientResponse.GoogleMapDetailClientResponse;
import fit5120.monash.edu.features.searchingService.dto.clientResponse.GoogleMapNearbyClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name="google-places", url = "https://places.googleapis.com")
public interface GoogleMapClient {

    @PostMapping("/v1/places:searchNearby")
    GoogleMapNearbyClientResponse searchNearby(
            @RequestHeader("X-goog-Api-Key") String apikey,
            @RequestHeader("X-Goog-FieldMask") String fieldMask,
            @RequestBody GoogleMapNearbyRequest googleMapNearbyRequest
    );


    @GetMapping("/v1/places/{placeId}")
    GoogleMapDetailClientResponse getDetails(
            @RequestHeader("X-goog-Api-Key") String apikey,
            @RequestHeader("X-Goog-FieldMask") String fieldMask,
            @PathVariable("placeId") String placeId
            );
}
