package com.Hechuyengia.Chuandoanbenh.service;

import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
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
            // Kiểm tra xem mã triệu chứng có tồn tại trong mảng truyền vào không
            if (!selectedSymptomCodes.contains(ma_trieu_chung)) {
                Long ma_trieu_chung_int = Long.parseLong(ma_trieu_chung);
                TrieuChungEntity trieuchungEntity = new TrieuChungEntity(ma_trieu_chung_int, ten_trieu_chung);
                trieuchungEntities.add(trieuchungEntity);

            }
        }
        return trieuchungEntities;
    }

    public List<BenhEntity> getSymptomsInSelectedBenh(List<String> danh_sach_tc) {
        List<Object[]> results = luatRepository.findSymptomsInSelectedBenh(danh_sach_tc, danh_sach_tc.size());
        List<BenhEntity> benhEntities = new ArrayList<>();

        for (Object[] result : results) {
            String ma_benh = result[0].toString();
            String ten_benh = result[1].toString();
            //String loai_he = result[2].toString();
           int ma_benh_int = Integer.parseInt(ma_benh);
            BenhEntity benhEntity = new BenhEntity(ma_benh_int, ten_benh);
            benhEntities.add(benhEntity);
        }
        return benhEntities;
    }
    public List<BenhEntity> getSymptomsInSelectedBenh1(List<String> danh_sach_tc) {
        List<Object[]> results = luatRepository.findSymptomsInSelectedBenh1(danh_sach_tc);
        List<BenhEntity> benhEntities = new ArrayList<>();

        for (Object[] result : results) {
            
            String ma_benh = result[0].toString();
            String ten_benh = result[1].toString();
            //String loai_he = result[2].toString();
            int ma_benh_int = Integer.parseInt(ma_benh);
            BenhEntity benhEntity = new BenhEntity(ma_benh_int, ten_benh);
            benhEntities.add(benhEntity);
        
            // Xử lý trường hợp mảng không đủ phần tử (nếu cần)
            // Ví dụ: log hoặc bỏ qua dòng này và tiếp tục với phần tử tiếp theo
            // Log.warn("Mảng không đủ phần tử: " + Arrays.toString(result));
        }
        
        return benhEntities;
    }
}
