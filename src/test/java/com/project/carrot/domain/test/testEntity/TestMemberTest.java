package com.project.carrot.domain.test.testEntity;

import com.project.carrot.domain.test.reposotory.Average;
import com.project.carrot.domain.test.reposotory.MemberAutoIncrementIdRepository;
import com.project.carrot.domain.test.reposotory.MemberSequentialUuidRepository;
import com.project.carrot.domain.test.reposotory.MemberUuidRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static java.lang.System.nanoTime;

@SpringBootTest
@Slf4j
@Transactional
class TestMemberTest {

    @Autowired
    MemberAutoIncrementIdRepository autoIncrementIdRepository;
    @Autowired
    MemberSequentialUuidRepository sequentialUuidRepository;
    @Autowired
    MemberUuidRepository uuidRepository;
    @Autowired
    EntityManager em;
    int numberOfRepetitions = 100000;
    int toMs = 1000000;


    @AfterEach
    void afterEach() {

        autoIncrementIdRepository.deleteAll();
        sequentialUuidRepository.deleteAll();
        uuidRepository.deleteAll();
    }

    @Test
    @DisplayName("평균")
    void average() {
        List<Long> autoIncrement = List.of(7636963834L, 5595468875L, 5935422750L, 3607311500L, 3365462584L, 4063286667L, 3250879208L, 3223789708L, 3782410750L, 3426906834L);
        Average autoIncrement_avg = new Average(autoIncrement);
        Long autoIncrement_ms = (autoIncrement_avg.getAverage() / toMs);

        List<Long> uuid = List.of(11042429125L, 10162473000L, 8622454875L, 8399379250L, 7155957000L, 6888015667L, 7004718625L, 6924356750L, 7558648125L, 9039871041L);
        Average uuid_avg = new Average(uuid);
        Long uuid_ms = (uuid_avg.getAverage() / toMs);

        List<Long> sequential = List.of(8500132292L, 7993350208L, 6901160583L, 7785815083L, 6571199958L, 6951364791L, 7017476000L, 6515922125L, 7553618625L, 8549684625L);
        Average sequential_avg = new Average(sequential);
        Long sequential_ms = (sequential_avg.getAverage() / toMs);

        List<Long> find_autoIncrement = List.of(625075583L, 148711083L, 161241833L, 129560792L, 111624250L, 135183750L, 113824375L, 113700833L, 137226917L, 94062459L);
        Average find_autoIncrement_avg = new Average(find_autoIncrement);
        Long find_autoIncrement_ms = (find_autoIncrement_avg.getAverage() / toMs);

        List<Long> find_uuid = List.of(280931292L, 322510500L, 184127958L, 175228458L, 162080833L, 162385792L, 174497916L, 138845500L, 164874208L, 182403333L);
        Average find_uuid_avg = new Average(find_autoIncrement);
        Long find_uuid_ms = (find_autoIncrement_avg.getAverage() / toMs);

        List<Long> find_sequential = List.of(169857583L, 216696500L, 146191917L, 144883667L, 174061833L, 140206875L, 148018667L, 145698333L, 142359917L, 189876084L);
        Average find_sequential_avg = new Average(find_autoIncrement);
        Long find_sequential_ms = (find_autoIncrement_avg.getAverage() / toMs);
    }


    @Test
    @DisplayName("test01")
    void test01() throws Exception {

        speedTest();

    }

    @Test
    @DisplayName("test02")
    void test02() throws Exception {

        speedTest();

    }

    @Test
    @DisplayName("test03")
    void test03() throws Exception {

        speedTest();

    }

    @Test
    @DisplayName("test04")
    void test04() throws Exception {

        speedTest();

    }

    @Test
    @DisplayName("test05")
    void test05() throws Exception {

        speedTest();

    }

    @Test
    @DisplayName("test06")
    void test06() throws Exception {

        speedTest();

    }

    @Test
    @DisplayName("test07")
    void test07() throws Exception {

        speedTest();

    }

    @Test
    @DisplayName("test08")
    void test08() throws Exception {

        speedTest();

    }

    @Test
    @DisplayName("test09")
    void test09() throws Exception {

        speedTest();

    }

    @Test
    @DisplayName("test10")
    void test10() throws Exception {

        speedTest();

    }

    private void speedTest() {
        Long autoIncrement = autoIncrement();
        Long uuid = uuid();
        Long sequenceUuid = sequenceUuid();

        Long incrementScan = autoIncrementScan();
        Long uuidScan = uuidScan();
        Long sequenceUuidScan = sequenceUuidScan();

        System.out.println("=====================================================================================");
        System.out.println("Insert AutoIncrement     nano time = " + autoIncrement + " and ms = " + (autoIncrement / toMs) + "ms");
        System.out.println("Insert UUID              nano time = " + uuid + " and ms = " + (uuid / toMs) + "ms");
        System.out.println("Insert Sequential_UUID   nano time = " + sequenceUuid + " and ms = " + (sequenceUuid / toMs) + "ms");

        System.out.println("Find All AutoIncrement   nano time = " + incrementScan + " and ms = " + (incrementScan / toMs) + "ms");
        System.out.println("Find All UUID            nano time = " + uuidScan + " and ms = " + (uuidScan / toMs) + "ms");
        System.out.println("Find All Sequential_UUID nano time = " + sequenceUuidScan + " and ms = " + (sequenceUuidScan / toMs) + "ms");
        System.out.println("=====================================================================================");
    }

    private Long autoIncrementScan() {
        Long start = nanoTime();
        autoIncrementIdRepository.findAll();
        Long end = nanoTime();
        return end - start;
    }

    private Long uuidScan() {
        Long start = nanoTime();
        uuidRepository.findAll();
        Long end = nanoTime();
        return end - start;
    }

    private Long sequenceUuidScan() {
        Long start = nanoTime();
        sequentialUuidRepository.findAll();
        Long end = nanoTime();
        return end - start;
    }

    private Long autoIncrement() {
        Long startIncrement = nanoTime();
        for (int i = 0; i < numberOfRepetitions; i++) {

            Member_Increment incrementId = Member_Increment.builder()
                    .loginId("test" + i)
                    .build();

            autoIncrementIdRepository.save(incrementId);
        }
        em.flush();
        em.clear();
        Long endIncrement = nanoTime();
        Long time = endIncrement - startIncrement;
        return time;
    }

    private Long uuid() {
        Long startIncrement = nanoTime();
        for (int i = 0; i < numberOfRepetitions; i++) {

            Member_uuid member_uuid = Member_uuid.builder()
                    .loginId("test" + i)
                    .build();

            uuidRepository.save(member_uuid);
        }
        em.flush();
        em.clear();
        Long endIncrement = nanoTime();
        Long time = endIncrement - startIncrement;
        return time;
    }

    private Long sequenceUuid() {
        Long start = nanoTime();
        for (int i = 0; i < numberOfRepetitions; i++) {

            Member_Sequential sequentialUuid = Member_Sequential.builder()
                    .loginId("test" + i)
                    .build();

            sequentialUuidRepository.save(sequentialUuid);
        }
        em.flush();
        em.clear();
        Long end = nanoTime();
        Long time = end - start;
        return time;
    }

}