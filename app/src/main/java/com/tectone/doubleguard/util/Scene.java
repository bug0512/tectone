package com.tectone.doubleguard.util;

import android.util.Log;

import com.tectone.doubleguard.SuggestListData;

import java.util.ArrayList;

/**
 * Created by JamesLee on 2014-11-02.
 */
public class Scene {
    SuggestListData s_data;
    String[] suggestList;
    ArrayList<SuggestListData> s_arrayData;

    public ArrayList<SuggestListData> getSuggesList(String badgeType) {
        Log.d("S BADGE TYPE", "====================== :" + badgeType);
        if (badgeType.equals("1,B,1")) {
            suggestList = new String[]{"LED로 옷장 습도 알림",
                    "집안 내 불쾌지수를 계산하여 에어솔루션 제안",
                    "온/습도를 측정하여 쾌적한 욕실 유지에 필요한 공조 시점 알림",
                    "LED로 실내 적정 온/습도 알림",
                    "가습기/제습기를 작동시켜 적정 습도 유지"};

        } else if (badgeType.equals("4,L,3")) {
            suggestList = new String[]{"부모님이 일어나시면 알림 메시지 받기",
                    "독서실 자리에 앉으면 부모님/생활 스터디원들에게 메시지 전송",
                    "공용 화장실/회의실에 사람이 들어갔는지를 알려줌",
                    "남자친구가 사준 인형을 강하게 흔들면 서운함 이모티콘 발송",
                    "현관문이 열리면 나에게 메시지 전송 (도어락 해킹 피해 방지)"};

        } else if (badgeType.equals("2,C,2")) {
            suggestList = new String[]{"화장실에서의 시간 흐름을 스피커로 알림",
                    "화장대에서 시간의 흐름을 스피커로 알려줌",
                    "불을 끄면 침대 옆 Sensor Badge가 무드등 처럼 자동으로 켜졌다가 서서히 소등",
                    "천상의 짜짜로니를 위한 물조림 시간을 스피커로 알려줌"};

        } else if (badgeType.equals("4,E,2")) {
            suggestList = new String[]{"무언가를 일정기간 동안 사용하지 않으면 알람(운동기구, 청소기 등)",
                    "컨디셔닝 운동할 때 휴식이 끝나는 시간을 스피커로 알려줘서 운동효과 증진",
                    "무궁화 꽃이 피었습니다 레크레이션에서 심판으로 활용",
                    "졸음금지. 잠이 들어 움직임이 없어지면 알람"};
        /*
        } else if(badgeType.equals("5,F,5")) {
            suggestList = new String[]{"집 앞에서 사람이 서성거리면 TV on",
                    "원룸에 들어서면 자동으로 PC on 상태로 대기",
                    "소파에 누으면 자동으로 TV  on"};
        */
        } else if (badgeType.equals("4,L,2")) {
            suggestList = new String[]{"창문을 노크하면 오늘의 날씨를 말해줌",
                    "일정 속도 이상으로 움직이면 효과음 재생 (운동, 놀이 시 활용)",
                    "세탁기 문을 열면 오늘 중 빨래지수가 가장 높은 시간을 알려줌",
                    "톡톡 두드리면 나에게 온 메시지를 확인해줌",
                    "세탁기 문을 열면 오늘 중 빨래지수가 가장 높은 시간을 알려줌"};

            //2014.11.19 추가
        } else if (badgeType.equals("1,I,4")) {
            suggestList = new String[]{"아기가 최적환경에 있을 수 있도록 자동으로 에어컨, 제습기, 에어워셔, 공기청정기를 제어함",
                    "옷장 내 습도에 따른 LED 색상 변화를 통하여 옷을 유지하기 좋은 최적의 상태를 알림",
                    "맥주를 병이 깨지지 않을 정도로 냉각시키고 싶을 때 함께 냉동실에 넣어두면, 알맞은 온도가 될 때 알림",
                    "냉동 육류/생선의 해동 상태를 LED 색상 변화를 통하여 알림"};

        } else if (badgeType.equals("5,F,5")) {
            suggestList = new String[]{"TV를 가까이서 시청하면 눈이 나빠질 수 있어서 아기가 TV 가까이 다가오면 전원이 꺼지고 멀어지면 다시 자동으로 켜짐",
                    "집 앞에서 사람이 서성거리면 TV on",
                    "아이가 위험한 곳에 접근하는 것을 감지하고, 스피커를 통하여 알림",
                    "가스레인지를 켜둔 상태로 자리를 비울 때, 잊지 않고 알림",
                    "아침 일찍 집 현관에서 오늘의 날씨를 알림",
                    "창문 안쪽에 움직임이 없는데 창문이 열리게 되는 경우 경고음을 재생",
                    "원룸에 들어서면 자동으로 PC on 상태로 대기",
                    "소파에 누으면 자동으로 TV  on"};

        } else if (badgeType.equals("5,F,2")) {
            suggestList = new String[]{"아기가 가까이 하지 말아야할 장소에 근접하면 경고음을 발생함",
                    "집 앞에서 사람이 서성거리면 TV on",
                    "아이가 위험한 곳에 접근하는 것을 감지하고, 스피커를 통하여 알림",
                    "가스레인지를 켜둔 상태로 자리를 비울 때, 잊지 않고 알림",
                    "아침 일찍 집 현관에서 오늘의 날씨를 알림",
                    "창문 안쪽에 움직임이 없는데 창문이 열리게 되는 경우 경고음을 재생"};

        } else {
            suggestList = new String[]{"가스레인지를 켜 둔 상태에서 자리를 비우면 알림",
                    "화장실에서의 시간 흐름을 LED 색 변화로 알림",
                    "집 앞에서 사람이 서성거리면 TV on",
                    "Sensor badge를 흔들면 반대 쪽 Sensor badge가 반짝임",
                    "LED로 옷장 습도 알림"};
        }

        s_arrayData = new ArrayList<SuggestListData>();

        for (int i = 0; i < suggestList.length; i++) {
            s_data = new SuggestListData(suggestList[i]);
            s_arrayData.add(s_data);
        }

        return s_arrayData;
    }

    public ArrayList<SuggestListData> getSuggesListTwo(String badgeTypeOne, String badgeTypeTwo) {

        Log.d("S BADGE TYPE1", "====================== :" + badgeTypeOne);
        Log.d("S BADGE TYPE2", "====================== :" + badgeTypeTwo);
        if ((badgeTypeOne.equals("1,A,2") && badgeTypeTwo.equals("6,N,2")) || (badgeTypeOne.equals("6,N,2") && badgeTypeTwo.equals("1,A,2"))) {
            suggestList = new String[]{"가스레인지를 켜 둔 상태에서 자리를 비우면 알림",
                    "방 안에 사람이 없는데 난방이 계속 돌아가고 있으면 알려줌",
                    "아이 열이 계속 나고 있는데 주변에 사람이 없으면 알림"};

        } else if ((badgeTypeOne.equals("4,L,2") && badgeTypeTwo.equals("6,G,2")) || (badgeTypeOne.equals("6,G,2") && badgeTypeTwo.equals("4,L,2"))) {
            suggestList = new String[]{"집을 나서기 전 날씨에 대한 간단한 Tip을 알려줌",
                    "집에 들어올 때 가볍게 나를 맞이해줌 (부재 중 댁 내 상황 안내)",
                    "창문 밖에서 문이 열리면 빈집털이가 침입했음을 알림",
                    "집에 들어올 때 최신음악 재생"};

        } else if ((badgeTypeOne.equals("3,K,4") && badgeTypeTwo.equals("6,G,1")) || (badgeTypeOne.equals("6,G,1") && badgeTypeTwo.equals("3,K,4"))) {
            suggestList = new String[]{"아기의 울음소리를 감지하여 주변에 있는 badge로 알림을 전달함",
                    "집에 남겨진 강아지가 짖거나 시끄럽게하는 경우, 주인 목소리를 재생하여 진정시킴",
                    "세탁기의 종료음이 울리면 거실 등의 조명색을 변화시켜 알림",
                    "자녀가 귀가한 후 냉장고 앞에 서면, 외출하신 어머니의 음성메모가 재생"};

        } else if ((badgeTypeOne.equals("4,L,1") && badgeTypeTwo.equals("4,E,1")) || (badgeTypeOne.equals("4,E,1") && badgeTypeTwo.equals("4,L,1"))) {
            suggestList = new String[]{"Sensor Badge를 흔들면 연인의 Sensor Badge에 신호를 보냄",
                    "집에 있는 반려견이 얼마나 움직이는지 Sensor Badge 빛으로 확인",
                    "나와 주변 사람의 움직이는 속도에 따라 빛이 변화함",
                    "레고로 만든 경찰차를 움직이면 싸이렌 불이 켜짐",
                    "자녀의 게임기에 붙여 놓아서 얼마나 오래 게임하는지를 관리",
                    "흔들어서 서빙을 요청하고, 서버가 오고 있는지를 LED 빛 변화를 통해 파악"};
        } else {
            suggestList = new String[]{"가스레인지를 켜 둔 상태에서 자리를 비우면 알림",
                    "화장실에서의 시간 흐름을 LED 색 변화로 알림",
                    "집 앞에서 사람이 서성거리면 TV on",
                    "Sensor badge를 흔들면 반대 쪽 Sensor badge가 반짝임",
                    "LED로 옷장 습도 알림"};
        }

        s_arrayData = new ArrayList<SuggestListData>();

        for (int i = 0; i < suggestList.length; i++) {
            s_data = new SuggestListData(suggestList[i]);
            s_arrayData.add(s_data);
        }

        return s_arrayData;
    }

    public ArrayList<SuggestListData> getSuggesListAdd(String badgeType) {
        Log.d("S BADGE TYPE", "====================== :" + badgeType);
        if (badgeType.equals("1B1")) {
            suggestList = new String[]{"LED로 옷장 습도 알림",
                    "집안 내 불쾌지수를 계산하여 에어솔루션 제안",
                    "온/습도를 측정하여 쾌적한 욕실 유지에 필요한 공조 시점 알림",
                    "LED로 실내 적정 온/습도 알림",
                    "가습기/제습기를 작동시켜 적정 습도 유지"};

        } else if (badgeType.equals("4L3")) {
            suggestList = new String[]{"부모님이 일어나시면 알림 메시지 받기",
                    "독서실 자리에 앉으면 부모님/생활 스터디원들에게 메시지 전송",
                    "공용 화장실/회의실에 사람이 들어갔는지를 알려줌",
                    "남자친구가 사준 인형을 강하게 흔들면 서운함 이모티콘 발송",
                    "현관문이 열리면 나에게 메시지 전송 (도어락 해킹 피해 방지)"};

        } else if (badgeType.equals("2C1")) {
            suggestList = new String[]{"화장실에서의 시간 흐름을 LED 색 변화로 알림",
                    "화장대에서 시간의 흐름을 색 변화로 알려줌",
                    "불을 끄면 침대 옆 Sensor Badge가 무드등 처럼 자동으로 켜졌다가 서서히 소등",
                    "천상의 짜짜로니를 위한 물조림 시간을 LED로 알려줌"};

        } else if (badgeType.equals("4E2")) {
            suggestList = new String[]{"무언가를 일정기간 동안 사용하지 않으면 알람(운동기구, 청소기 등)",
                    "컨디셔닝 운동할 때 휴식이 끝나는 시간을 스피커로 알려줘서 운동효과 증진",
                    "무궁화 꽃이 피었습니다 레크레이션에서 심판으로 활용",
                    "졸음금지. 잠이 들어 움직임이 없어지면 알람"};
        /*
        } else if(badgeType.equals("5F5")) {
            suggestList = new String[]{"집 앞에서 사람이 서성거리면 TV on",
                    "원룸에 들어서면 자동으로 PC on 상태로 대기",
                    "소파에 누으면 자동으로 TV  on"};
        */
        } else if (badgeType.equals("1I4")) {
            suggestList = new String[]{"아기가 최적환경에 있을 수 있도록 자동으로 에어컨, 제습기, 에어워셔, 공기청정기를 제어함",
                    "옷장 내 습도에 따른 LED 색상 변화를 통하여 옷을 유지하기 좋은 최적의 상태를 알림",
                    "맥주를 병이 깨지지 않을 정도로 냉각시키고 싶을 때 함께 냉동실에 넣어두면, 알맞은 온도가 될 때 알림",
                    "냉동 육류/생선의 해동 상태를 LED 색상 변화를 통하여 알림"};

        } else if (badgeType.equals("5F5")) {
            suggestList = new String[]{"TV를 가까이서 시청하면 눈이 나빠질 수 있어서 아기가 TV 가까이 다가오면 전원이 꺼지고 멀어지면 다시 자동으로 켜짐",
                    "집 앞에서 사람이 서성거리면 TV on",
                    "아이가 위험한 곳에 접근하는 것을 감지하고, 스피커를 통하여 알림",
                    "가스레인지를 켜둔 상태로 자리를 비울 때, 잊지 않고 알림",
                    "아침 일찍 집 현관에서 오늘의 날씨를 알림",
                    "창문 안쪽에 움직임이 없는데 창문이 열리게 되는 경우 경고음을 재생",
                    "원룸에 들어서면 자동으로 PC on 상태로 대기",
                    "소파에 누으면 자동으로 TV  on"};

        } else if (badgeType.equals("5F2")) {
            suggestList = new String[]{"아기가 가까이 하지 말아야할 장소에 근접하면 경고음을 발생함",
                    "집 앞에서 사람이 서성거리면 TV on",
                    "아이가 위험한 곳에 접근하는 것을 감지하고, 스피커를 통하여 알림",
                    "가스레인지를 켜둔 상태로 자리를 비울 때, 잊지 않고 알림",
                    "아침 일찍 집 현관에서 오늘의 날씨를 알림",
                    "창문 안쪽에 움직임이 없는데 창문이 열리게 되는 경우 경고음을 재생"};
        } else {
            suggestList = new String[]{"가스레인지를 켜 둔 상태에서 자리를 비우면 알림",
                    "화장실에서의 시간 흐름을 LED 색 변화로 알림",
                    "집 앞에서 사람이 서성거리면 TV on",
                    "Sensor badge를 흔들면 반대 쪽 Sensor badge가 반짝임",
                    "Sensor badge를 흔들면 반대 쪽 Sensor badge가 반짝임",
                    "Sensor badge를 흔들면 반대 쪽 Sensor badge가 반짝임",
                    "Sensor badge를 흔들면 반대 쪽 Sensor badge가 반짝임",
                    "LED로 옷장 습도 알림"};
        }

        s_arrayData = new ArrayList<SuggestListData>();

        for (int i = 0; i < suggestList.length; i++) {
            s_data = new SuggestListData(suggestList[i]);
            s_arrayData.add(s_data);
        }

        return s_arrayData;
    }

    public ArrayList<SuggestListData> getSuggesListTwoAdd(String badgeTypeOne, String badgeTypeTwo) {

        Log.d("S BADGE TYPE1", "====================== :" + badgeTypeOne);
        Log.d("S BADGE TYPE2", "====================== :" + badgeTypeTwo);
        if ((badgeTypeOne.equals("1A2") && badgeTypeTwo.equals("6N2")) || (badgeTypeOne.equals("6N2") && badgeTypeTwo.equals("1A2"))) {
            suggestList = new String[]{"가스레인지를 켜 둔 상태에서 자리를 비우면 알림",
                    "방 안에 사람이 없는데 난방이 계속 돌아가고 있으면 알려줌",
                    "아이 열이 계속 나고 있는데 주변에 사람이 없으면 알림"};

        } else if ((badgeTypeOne.equals("4L2") && badgeTypeTwo.equals("6G2")) || (badgeTypeOne.equals("6G2") && badgeTypeTwo.equals("4L2"))) {
            suggestList = new String[]{"집을 나서기 전 날씨에 대한 간단한 Tip을 알려줌",
                    "집에 들어올 때 가볍게 나를 맞이해줌 (부재 중 댁 내 상황 안내)",
                    "창문 밖에서 문이 열리면 빈집털이가 침입했음을 알림",
                    "집에 들어올 때 최신음악 재생"};

        } else if ((badgeTypeOne.equals("3K4") && badgeTypeTwo.equals("6G1")) || (badgeTypeOne.equals("6G1") && badgeTypeTwo.equals("3K4"))) {
            suggestList = new String[]{"아기의 울음소리를 감지하여 주변에 있는 badge로 알림을 전달함",
                    "집에 남겨진 강아지가 짖거나 시끄럽게하는 경우, 주인 목소리를 재생하여 진정시킴",
                    "세탁기의 종료음이 울리면 거실 등의 조명색을 변화시켜 알림",
                    "자녀가 귀가한 후 냉장고 앞에 서면, 외출하신 어머니의 음성메모가 재생"};

        } else if ((badgeTypeOne.equals("4L1") && badgeTypeTwo.equals("4E1")) || (badgeTypeOne.equals("4E1") && badgeTypeTwo.equals("4L1"))) {
            suggestList = new String[]{"Sensor Badge를 흔들면 연인의 Sensor Badge에 신호를 보냄",
                    "집에 있는 반려견이 얼마나 움직이는지 Sensor Badge 빛으로 확인",
                    "나와 주변 사람의 움직이는 속도에 따라 빛이 변화함",
                    "레고로 만든 경찰차를 움직이면 싸이렌 불이 켜짐",
                    "자녀의 게임기에 붙여 놓아서 얼마나 오래 게임하는지를 관리",
                    "흔들어서 서빙을 요청하고, 서버가 오고 있는지를 LED 빛 변화를 통해 파악"};

        } else {
            suggestList = new String[]{"가스레인지를 켜 둔 상태에서 자리를 비우면 알림",
                    "화장실에서의 시간 흐름을 LED 색 변화로 알림",
                    "집 앞에서 사람이 서성거리면 TV on",
                    "Sensor badge를 흔들면 반대 쪽 Sensor badge가 반짝임",
                    "LED로 옷장 습도 알림"};
        }

        s_arrayData = new ArrayList<SuggestListData>();

        for (int i = 0; i < suggestList.length; i++) {
            s_data = new SuggestListData(suggestList[i]);
            s_arrayData.add(s_data);
        }

        return s_arrayData;
    }


}
