package az.unibank.unitech.controller;

import az.unibank.unitech.data.dto.request.CreateAccountRequestDto;
import az.unibank.unitech.data.dto.request.TransferRequestDto;
import az.unibank.unitech.data.dto.response.SavedResponseDto;
import az.unibank.unitech.data.entity.User;
import az.unibank.unitech.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = AccountController.class)
public class AccountControllerTests {

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final Validator validator= Validation.buildDefaultValidatorFactory().getValidator();

    private CreateAccountRequestDto request = new CreateAccountRequestDto();

    private User user = new User();
    private TransferRequestDto transferRequestDto = new TransferRequestDto();

    @BeforeEach
    void setup() {
        user.setId(1L);
        user.setPassword("test123");
        user.setFin("BCD1234");
        user.setEmail("test@gmail.com");
        user.setCreatedDate(new Date());
    }

    @BeforeEach
    void setupTransferRequestDto() {
        transferRequestDto.setAccountId(1L);
        transferRequestDto.setPrice("AZN");
        transferRequestDto.setUserId(1L);
        transferRequestDto.setFin("ABC1234");
        transferRequestDto.setEmail("test@gmail.com");
        transferRequestDto.setAmount(0.0);
    }

    @Test
    void createAccount() throws Exception {
        SavedResponseDto response = new SavedResponseDto();
        response.setId(1L);
        request = new CreateAccountRequestDto();
        request.setUserId(user.getId());
        request.setAmount(new BigDecimal(100));
        when(accountService.create(request)).thenReturn(response);
        mockMvc.perform(post("/api/v1/accounts/create-account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void finMusNotBeNull(){
        String finNumber="ABC1234";
        TransferRequestDto transferRequestDto1=new TransferRequestDto();
        transferRequestDto1.setFin(finNumber);
        Set<ConstraintViolation<TransferRequestDto>>violations=validator.validate(transferRequestDto1);
        assertThat(violations).isNotEmpty();
    }

}
