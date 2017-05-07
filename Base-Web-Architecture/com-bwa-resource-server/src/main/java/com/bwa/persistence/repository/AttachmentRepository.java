package com.bwa.persistence.repository;

import com.bwa.persistence.model.Attachment;
import com.bwa.persistence.model.Customer;

import java.util.List;

/**
 * Created by Hassnan.Ali on 5/4/2017.
 */
public interface AttachmentRepository extends BaseRepository<Attachment,Long> {

    List<Attachment> findByCustomer(Customer customer);

}
