package com.Hechuyengia.Chuandoanbenh.service;

import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import com.Hechuyengia.Chuandoanbenh.repository.LuatRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author tranm
 */
@Service
public class DiagnosisService {
     private final LuatRepository luatRepository;

    @Autowired
    public DiagnosisService(LuatRepository luatRepository) {
        this.luatRepository = luatRepository;
    }

    public List<TrieuChungEntity> getSymptomsInSelectedLuats(List<String> selectedSymptomCodes) {
    List<Object[]> results = luatRepository.findSymptomsInSelectedLuats(selectedSymptomCodes);
    List<TrieuChungEntity> trieuchungEntities = new ArrayList<>();

    for (Object[] result : results) {
        String ma_trieu_chung = result[0].toString();
        String ten_trieu_chung = result[1].toString();
         int ma_trieu_chung_int = Integer.parseInt(ma_trieu_chung);
        TrieuChungEntity trieuchungEntity = new TrieuChungEntity(ma_trieu_chung_int, ten_trieu_chung);
        trieuchungEntities.add(trieuchungEntity);
    }

    return trieuchungEntities;
}

    
    
}
