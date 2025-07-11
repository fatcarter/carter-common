package cn.fatcarter.common.util;

import lombok.Data;

import java.util.List;

public class StreamUtilsTest {
    private List<GroupDto> dtos = List.of(
        GroupDto.of(11864, 4622),
        GroupDto.of(11864, 1234),
        GroupDto.of(11864, 6547),
        GroupDto.of(11864, 4567),
        GroupDto.of(11864, 6546),

        GroupDto.of(22680, 4622),
        GroupDto.of(22680, 8979),
        GroupDto.of(22680, 7897),
        GroupDto.of(22680, 5231),
        GroupDto.of(22680, 1457)
    );

    @Data
    public static class GroupDto {
        private Integer companyId;
        private Integer projectId;

        public static GroupDto of(Integer companyId, Integer projectId) {
            GroupDto groupDto = new GroupDto();
            groupDto.companyId = companyId;
            groupDto.projectId = projectId;
            return groupDto;
        }
    }

}
