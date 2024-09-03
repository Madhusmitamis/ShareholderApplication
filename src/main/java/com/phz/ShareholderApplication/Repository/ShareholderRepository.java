package com.phz.ShareholderApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phz.ShareholderApplication.Model.Shareholder;

public interface ShareholderRepository extends JpaRepository<Shareholder, Long> {

    

}
