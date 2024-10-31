package io.member.impl;

import java.util.Scanner;

public class MemberConsoleMain {

    private static final MemoryMemberRepository repository = new MemoryMemberRepository();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("1. 회원등록 | 2. 회원목록조회 | 3. 종료");
            System.out.printf("선텍: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); //newLine 제거

            switch (choice){
                case 1:
                    //회원등록
                    break;
                case 2:
                    //회원목록조회
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 입력하세요");
}
        }
    }
}
