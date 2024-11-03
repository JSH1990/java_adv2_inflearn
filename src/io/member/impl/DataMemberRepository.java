package io.member.impl;

import io.member.Member;
import io.member.MemberRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataMemberRepository implements MemberRepository {
    private static final String FILE_PATH  = "temp/members-data.dat";

    @Override
    public void add(Member member) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_PATH, true))){
            dos.writeUTF(member.getId());
            dos.writeUTF(member.getName());
            dos.writeInt(member.getAge());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /*
    ex)
    id1name120
    -> 3id 5name1 20(4byte) 해서 출력

    저장된 파일예시
    3id1(2byte(문자길이) + 3byte)
    5name1(2byte(문자길이) + 5byte)
    20(4byte)

     */
    @Override
    public List<Member> findAll() {
        ArrayList<Member> members = new ArrayList<>();
        try(DataInputStream dis = new DataInputStream(new FileInputStream(FILE_PATH))){
            while (dis.available() > 0){
                Member member = new Member(dis.readUTF(), dis.readUTF(), dis.readInt());
                members.add(member);
            }
            return members;

        }catch (FileNotFoundException e){
           return new ArrayList<>();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
}
