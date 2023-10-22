package com.stringconcat.people

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

/**
 * @author gnasyrova
 */
@SpringBootTest
@AutoConfigureMockMvc
class PeopleApplicationTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `test me url`() {
        val contentAsString = mockMvc.get("/me")
            .andExpect { status { is2xxSuccessful } }
            .andReturn()
            .response.contentAsString
        contentAsString
            .apply { contains("make the easy things easy, and the hard things possible") }
    }
}
