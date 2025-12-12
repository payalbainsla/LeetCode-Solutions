class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int[] mentions = new int[numberOfUsers];
        boolean[] online = new boolean[numberOfUsers];
        int[] backOnlineTime = new int[numberOfUsers];

        Arrays.fill(online, true);
        Arrays.fill(backOnlineTime, -1);

        Collections.sort(events,(a,b) -> {
            int t1 = Integer.parseInt(a.get(1));
            int t2 = Integer.parseInt(b.get(1));

            if(t1 != t2) return t1-t2;

            if(a.get(0).equals("OFFLINE") && b.get(0).equals("MESSAGE")) return -1;
            if(a.get(0).equals("MESSAGE") && b.get(0).equals("OFFLINE")) return 1;
            return 0;
        });

        for(List<String> e:events){
            String type = e.get(0);
            int time = Integer.parseInt(e.get(1));

            for(int i=0; i<numberOfUsers; i++) {
                if(!online[i] && backOnlineTime[i] <= time){
                    online[i] = true;
                }
            }

            //offline
            if(type.equals("OFFLINE")) {
                int user = Integer.parseInt(e.get(2));
                online[user] = false;
                backOnlineTime[user] = time + 60;
            } else{
                String msg = e.get(2);
                if(msg.equals("ALL")){
                    for(int i=0; i<numberOfUsers; i++){
                        mentions[i]++;
                    }
                }else if(msg.equals("HERE")){
                       for(int i=0; i<numberOfUsers; i++) {
                        if(online[i]){
                            mentions[i]++;
                        }
                       }
                } else{
                    String[] parts = msg.split(" ");
                    for(String p:parts){
                        if(p.startsWith("id")){
                            int id = Integer.parseInt(p.substring(2));
                            mentions[id]++;
                        }
                    }
                }
            }
        }
        return mentions;
    }
}