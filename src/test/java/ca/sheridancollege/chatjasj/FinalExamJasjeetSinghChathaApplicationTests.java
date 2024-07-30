package ca.sheridancollege.chatjasj;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FinalExamJasjeetSinghChathaApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	
	  @Test
	    @WithMockUser(roles = "GUEST")
	    public void testAccessUserPageWithInvalidRole() throws Exception {
	        mockMvc.perform(get("/add"))
	                .andExpect(status().isFound())
	                .andExpect(redirectedUrl("/deny"));
	    }
	
	
	@Test
	@WithMockUser(roles = "BOSS")
	public void testLoadingViewPageAsVendor() throws Exception {
	    this.mockMvc.perform(get("/view"))
	            .andExpect(status().isOk())
	            .andExpect(view().name("View.html"));
	}
	
	@Test
	@WithMockUser(roles = "BOSS")
	public void testLoadingemailAsBoss() throws Exception {
	    this.mockMvc.perform(get("/email")) 
	            .andExpect(status().isOk())
	            .andExpect(view().name("email.html"));
	}
	@Test
	@WithMockUser(roles = "GUEST")
	public void testLoadingemailAsGuest() throws Exception {
	    this.mockMvc.perform(get("/email")) 
	            .andExpect(status().isOk())
	            .andExpect(view().name("email.html"));
	}
	@Test
	public void testLoadingRegisterPage() throws Exception {
	    this.mockMvc.perform(get("/register"))
	            .andExpect(status().isOk())
	            .andExpect(view().name("registration.html"));
	}

	@Test
	public void testLoadingHomePage() throws Exception {
	    this.mockMvc.perform(get("/"))
	            .andExpect(status().isOk())
	            .andExpect(view().name("index.html")); 
	}
	@Test
    public void testAccessUserPageWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrlPattern("**/login"));
    }

	@Test
	void contextLoads() {
	}
}
