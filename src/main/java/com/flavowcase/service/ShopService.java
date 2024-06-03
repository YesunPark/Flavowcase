package com.flavowcase.service;

import com.flavowcase.common.CustomException;
import com.flavowcase.domain.Member;
import com.flavowcase.domain.MemberRepository;
import com.flavowcase.domain.Shop;
import com.flavowcase.domain.ShopRepository;
import com.flavowcase.dto.ShopCreateRequest;
import com.flavowcase.dto.kakao.KakaoInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.flavowcase.common.ErrorCode.MEMBER_NOT_EXISTS;

@RequiredArgsConstructor
@Service
public class ShopService {

    private final ShopRepository shopRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Value("${authorization.header}")
    private String AUTHORIZATION_HEADER;

    // 매장 등록
    @Transactional
    public Long createShop(String token, ShopCreateRequest request) {
        token = token.substring(AUTHORIZATION_HEADER.length());
        KakaoInfoResponse memberInfo = memberService.getMemberInfo(token);
        Long memberId = memberInfo.getId();
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(MEMBER_NOT_EXISTS)
        );

        Shop shop = Shop.builder()
                .member(member)
                .name(request.getName())
                .address(request.getAddress())
                .thumbnailUrl(request.getThumbnail())
                .contactNumber(request.getContactNumber())
                .description(request.getDescription())
                .notice(request.getNotice())
                .build();

        Shop savedShop = shopRepository.save(shop);

        return savedShop.getId();
    }
}
