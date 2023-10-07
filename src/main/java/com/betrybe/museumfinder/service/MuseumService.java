package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Camada de serviço MuseumService.
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  private final MuseumFakeDatabase database;

  @Autowired
  public MuseumService(MuseumFakeDatabase database) {
    this.database = database;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    if (!CoordinateUtil.isCoordinateValid(coordinate)) {
      throw new InvalidCoordinateException();
    }
    Optional<Museum> closestMuseum = database.getClosestMuseum(coordinate, maxDistance);
    if (closestMuseum.isPresent()) {
      return closestMuseum.get();
    } else {
      throw new MuseumNotFoundException();
    }
  }

  @Override
  public Museum createMuseum(Museum museum) {
    if (CoordinateUtil.isCoordinateValid(museum.getCoordinate())) {
      Museum newMuseum = database.saveMuseum(museum);

      return newMuseum;
    } else {
      throw new InvalidCoordinateException();
    }
  }

  @Override
  public Museum getMuseum(Long id) {
    return null;
  }
}
