package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AbstractCassandraTest;
import com.mycompany.myapp.SecondApp;

import com.mycompany.myapp.domain.Checkfee;
import com.mycompany.myapp.repository.CheckfeeRepository;
import com.mycompany.myapp.service.CheckfeeService;
import com.mycompany.myapp.service.dto.CheckfeeDTO;
import com.mycompany.myapp.service.mapper.CheckfeeMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CheckfeeResource REST controller.
 *
 * @see CheckfeeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecondApp.class)
public class CheckfeeResourceIntTest extends AbstractCassandraTest {

    private static final Integer DEFAULT_FEEAMOUNT = 1;
    private static final Integer UPDATED_FEEAMOUNT = 2;

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private CheckfeeRepository checkfeeRepository;

    @Autowired
    private CheckfeeMapper checkfeeMapper;

    @Autowired
    private CheckfeeService checkfeeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restCheckfeeMockMvc;

    private Checkfee checkfee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CheckfeeResource checkfeeResource = new CheckfeeResource(checkfeeService);
        this.restCheckfeeMockMvc = MockMvcBuilders.standaloneSetup(checkfeeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Checkfee createEntity() {
        Checkfee checkfee = new Checkfee()
            .feeamount(DEFAULT_FEEAMOUNT)
            .reason(DEFAULT_REASON)
            .status(DEFAULT_STATUS);
        return checkfee;
    }

    @Before
    public void initTest() {
        checkfeeRepository.deleteAll();
        checkfee = createEntity();
    }

    @Test
    public void createCheckfee() throws Exception {
        int databaseSizeBeforeCreate = checkfeeRepository.findAll().size();

        // Create the Checkfee
        CheckfeeDTO checkfeeDTO = checkfeeMapper.toDto(checkfee);
        restCheckfeeMockMvc.perform(post("/api/checkfees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(checkfeeDTO)))
            .andExpect(status().isCreated());

        // Validate the Checkfee in the database
        List<Checkfee> checkfeeList = checkfeeRepository.findAll();
        assertThat(checkfeeList).hasSize(databaseSizeBeforeCreate + 1);
        Checkfee testCheckfee = checkfeeList.get(checkfeeList.size() - 1);
        assertThat(testCheckfee.getFeeamount()).isEqualTo(DEFAULT_FEEAMOUNT);
        assertThat(testCheckfee.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testCheckfee.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    public void createCheckfeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = checkfeeRepository.findAll().size();

        // Create the Checkfee with an existing ID
        checkfee.setId(UUID.randomUUID());
        CheckfeeDTO checkfeeDTO = checkfeeMapper.toDto(checkfee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCheckfeeMockMvc.perform(post("/api/checkfees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(checkfeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Checkfee> checkfeeList = checkfeeRepository.findAll();
        assertThat(checkfeeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllCheckfees() throws Exception {
        // Initialize the database
        checkfeeRepository.save(checkfee);

        // Get all the checkfeeList
        restCheckfeeMockMvc.perform(get("/api/checkfees"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(checkfee.getId().toString())))
            .andExpect(jsonPath("$.[*].feeamount").value(hasItem(DEFAULT_FEEAMOUNT)))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    public void getCheckfee() throws Exception {
        // Initialize the database
        checkfeeRepository.save(checkfee);

        // Get the checkfee
        restCheckfeeMockMvc.perform(get("/api/checkfees/{id}", checkfee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(checkfee.getId().toString()))
            .andExpect(jsonPath("$.feeamount").value(DEFAULT_FEEAMOUNT))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    public void getNonExistingCheckfee() throws Exception {
        // Get the checkfee
        restCheckfeeMockMvc.perform(get("/api/checkfees/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCheckfee() throws Exception {
        // Initialize the database
        checkfeeRepository.save(checkfee);
        int databaseSizeBeforeUpdate = checkfeeRepository.findAll().size();

        // Update the checkfee
        Checkfee updatedCheckfee = checkfeeRepository.findOne(checkfee.getId());
        updatedCheckfee
            .feeamount(UPDATED_FEEAMOUNT)
            .reason(UPDATED_REASON)
            .status(UPDATED_STATUS);
        CheckfeeDTO checkfeeDTO = checkfeeMapper.toDto(updatedCheckfee);

        restCheckfeeMockMvc.perform(put("/api/checkfees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(checkfeeDTO)))
            .andExpect(status().isOk());

        // Validate the Checkfee in the database
        List<Checkfee> checkfeeList = checkfeeRepository.findAll();
        assertThat(checkfeeList).hasSize(databaseSizeBeforeUpdate);
        Checkfee testCheckfee = checkfeeList.get(checkfeeList.size() - 1);
        assertThat(testCheckfee.getFeeamount()).isEqualTo(UPDATED_FEEAMOUNT);
        assertThat(testCheckfee.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testCheckfee.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    public void updateNonExistingCheckfee() throws Exception {
        int databaseSizeBeforeUpdate = checkfeeRepository.findAll().size();

        // Create the Checkfee
        CheckfeeDTO checkfeeDTO = checkfeeMapper.toDto(checkfee);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCheckfeeMockMvc.perform(put("/api/checkfees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(checkfeeDTO)))
            .andExpect(status().isCreated());

        // Validate the Checkfee in the database
        List<Checkfee> checkfeeList = checkfeeRepository.findAll();
        assertThat(checkfeeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteCheckfee() throws Exception {
        // Initialize the database
        checkfeeRepository.save(checkfee);
        int databaseSizeBeforeDelete = checkfeeRepository.findAll().size();

        // Get the checkfee
        restCheckfeeMockMvc.perform(delete("/api/checkfees/{id}", checkfee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Checkfee> checkfeeList = checkfeeRepository.findAll();
        assertThat(checkfeeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Checkfee.class);
        Checkfee checkfee1 = new Checkfee();
        checkfee1.setId(UUID.randomUUID());
        Checkfee checkfee2 = new Checkfee();
        checkfee2.setId(checkfee1.getId());
        assertThat(checkfee1).isEqualTo(checkfee2);
        checkfee2.setId(UUID.randomUUID());
        assertThat(checkfee1).isNotEqualTo(checkfee2);
        checkfee1.setId(null);
        assertThat(checkfee1).isNotEqualTo(checkfee2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CheckfeeDTO.class);
        CheckfeeDTO checkfeeDTO1 = new CheckfeeDTO();
        checkfeeDTO1.setId(UUID.randomUUID());
        CheckfeeDTO checkfeeDTO2 = new CheckfeeDTO();
        assertThat(checkfeeDTO1).isNotEqualTo(checkfeeDTO2);
        checkfeeDTO2.setId(checkfeeDTO1.getId());
        assertThat(checkfeeDTO1).isEqualTo(checkfeeDTO2);
        checkfeeDTO2.setId(UUID.randomUUID());
        assertThat(checkfeeDTO1).isNotEqualTo(checkfeeDTO2);
        checkfeeDTO1.setId(null);
        assertThat(checkfeeDTO1).isNotEqualTo(checkfeeDTO2);
    }
}
