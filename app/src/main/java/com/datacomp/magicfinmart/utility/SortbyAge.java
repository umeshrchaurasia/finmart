package com.datacomp.magicfinmart.utility;

import java.util.Comparator;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MemberListEntity;

public class SortbyAge implements Comparator<MemberListEntity> {
    // Used for sorting in ascending order of age
    public int compare(MemberListEntity a, MemberListEntity b) {
        return a.getAge() - b.getAge();
    }
}