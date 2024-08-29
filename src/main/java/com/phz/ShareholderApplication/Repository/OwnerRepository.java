package com.phz.ShareholderApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phz.ShareholderApplication.Model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
