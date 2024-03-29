package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Camada de controle MuseumController.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {
  private final MuseumServiceInterface service;

  @Autowired
  public MuseumController(MuseumServiceInterface service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody Museum museum) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(service.createMuseum(museum));
  }

  @GetMapping("/closest")
  public ResponseEntity<Museum> getClosestMuseum(@RequestParam("lat") Double lat,
      @RequestParam("lng") Double lng, @RequestParam("max_dist_km") Double maxDistKm) {
    Coordinate coordinate = new Coordinate(lat, lng);
    return ResponseEntity.ok(service.getClosestMuseum(coordinate, maxDistKm));
  }
}
