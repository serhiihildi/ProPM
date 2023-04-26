//package com.hildi.propm.model;
//
//import com.hildi.propm.dto.TeamDto;
//import com.hildi.propm.repository.ProjectRepository;
//import com.hildi.propm.repository.TeamRepository;
//import com.hildi.propm.repository.UserRepository;
//import com.hildi.propm.services.TeamService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.AssertEquals.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class TeamTest {
//
//    @Mock
//    private ProjectRepository projectRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private TeamRepository teamRepository;
//
//    @InjectMocks
//    private TeamService teamService;
//
//    @Test
//    void createTeam_ValidInput_Success() {
//        // arrange
//        TeamDto teamDto = new TeamDto("Test Team");
//        User user = new User("testuser", "testpassword", "Test", "User");
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//
//        // act
//        Team team = teamService.createTeam(teamDto, 1L);
//
//        // assert
//        assertEquals(teamDto.getName(), team.getName());
//        assertEquals(1, team.getMembers().size());
//        assertEquals(user, team.getMembers().get(0));
//    }
//
//    @Test
//    void getTeamById_ValidInput_Success() {
//        // arrange
//        Long teamId = 1L;
//        Team team = new Team("Test Team");
//        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
//
//        // act
//        Team resultTeam = teamService.getTeamById(teamId);
//
//        // assert
//        assertEquals(team, resultTeam);
//    }
//
//    @Test
//    void addMember_ValidInput_Success() {
//        // arrange
//        Long teamId = 1L;
//        Long userId = 2L;
//        Team team = new Team("Test Team");
//        User user = new User("testuser", "testpassword", "Test", "User");
//        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//
//        // act
//        teamService.addMember(teamId, userId);
//
//        // assert
//        assertEquals(1, team.getMembers().size());
//        assertEquals(user, team.getMembers().get(0));
//    }
//
//    @Test
//    void removeMember_ValidInput_Success() {
//        // arrange
//        Long teamId = 1L;
//        Long userId = 2L;
//        Team team = new Team("Test Team");
//        User user = new User("testuser", "testpassword", "Test", "User");
//        team.addMember(user);
//        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//
//        // act
//        teamService.removeMember(teamId, userId);
//
//        // assert
//        assertEquals(0, team.getMembers().size());
//    }
//
//    @Test
//    void deleteTeam_ValidInput_Success() {
//        // arrange
//        Long teamId = 1L;
//        Team team = new Team("Test Team");
//        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
//
//        // act
//        teamService.deleteTeam(teamId);
//
//        // assert
//        verify(teamRepository, times(1)).deleteById(teamId);
//    }
//}
//
