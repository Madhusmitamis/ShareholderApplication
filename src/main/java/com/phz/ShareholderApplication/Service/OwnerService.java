package com.phz.ShareholderApplication.Service;

import com.phz.ShareholderApplication.Model.Owner;

import java.util.List;

public interface OwnerService {
    List<Owner> getAllOwners();
    Owner saveOwner(Owner owner);
}
