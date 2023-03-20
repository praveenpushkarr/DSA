package Hashmap;

import java.util.*;

//HashMap: HashMap<key,value> name =new HashMap<>(); put,get,containsKey,keySet,remove
//HashSet: HashSet<key> name =new HashSet<>(); add,contains,remove

public class main {
    public static void main(String[] args){
        // this is how we initialize hashmap;
        HashMap<String,Integer> hm =new HashMap<>();
        // hm.put(key,value) ; if key is already present then it increases the value else it inserts the key in the map.
        hm.put("India",130);
        hm.put("US",10);
        hm.put("UK",30);
        hm.put("PK",40);
        System.out.println(hm);
        // hm.get("key") ; if key is present in the map then it returns the value eles it will return null.
        System.out.println(hm.get("India"));
        // hm.containsKey("key") ; if key is present then it gives true else returns false.
        System.out.println(hm.containsKey("India"));
        // hm.remove(key) ; removes the key and value from the hashmap.
        hm.remove("PK");
        System.out.println(hm);

        // hm.keySet() ; collection of all keys in tht hm
        Set<String> keys=hm.keySet();
        System.out.println(keys);
        for(String key:hm.keySet()){
            int val=hm.get(key);
            System.out.println(key+" "+ val);
        }
    }   
}

// ****************************************************************************************************************************
// Question : Higest Frequency Character 
class HigestFrequencyCharacter {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner s=new Scanner(System.in);
        String str=s.nextLine();
        HashMap<Character,Integer> hm=new HashMap<>();
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            if(hm.containsKey(ch)){
                int of=hm.get(ch);
                int nf=of+1;
                hm.put(ch,nf);
            }
            else
            {
                hm.put(ch,1);
            }
        }
        int mf=hm.get(str.charAt(0));
        char mch=str.charAt(0);
        for(Character ch: hm.keySet()){
            if(hm.get(ch)>mf){
                mf=hm.get(ch);
                mch=ch;
            }
        }
        System.out.println(mch);
    }

}

// ********************************************************************************************************************************
// Question: Get common elements 1 (used hashset)
class GetCommonElement{

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner s=new Scanner(System.in);
        int n1=s.nextInt();
        int a1[]=new int[n1];
        for(int i=0;i<n1;i++){
            a1[i]=s.nextInt();
        }
        int n2=s.nextInt();
        int a2[]=new int[n2];
        for(int i=0;i<n2;i++){
            a2[i]=s.nextInt();
        }
    
        HashSet<Integer> hm=new HashSet<>();
        for(int val:a1){
            hm.add(val);
        }
        for(int val:a2){
            if(hm.contains(val)){
                System.out.println(val);
                hm.remove(val);
            }
        }
     }
    }

//********************************************************************************************************************************
// Question: Get Common Element 2
//eg: a1=[1,1,1,2,2,3,4] , a2=[1,1,2,2,2,3,5] , output= 1,1,2,2,3
class GetCommonElement2{

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner s=new Scanner(System.in);
        int n1=s.nextInt();
        int a1[]=new int[n1];
        for(int i=0;i<n1;i++){
            a1[i]=s.nextInt();
        }
        int n2=s.nextInt();
        int a2[]=new int[n2];
        for(int i=0;i<n2;i++){
            a2[i]=s.nextInt();
        }
    
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int val:a1){
            if(hm.containsKey(val)){
                int of=hm.get(val);
                int nf=of+1;
                hm.put(val,nf);
            }
            else hm.put(val,1);
        }
        for(int val:a2){
            if(hm.containsKey(val)){
                if(hm.get(val)>0){
                    System.out.println(val);
                    int of=hm.get(val);
                    int nf=of-1;
                    hm.put(val,nf);
                }
            }
        }
     }
}

//*********************************************************************************************************************
// Question: Longest Consecutive Sequence
class LongestConsecutiveSequence{

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=s.nextInt();
        }
        HashMap<Integer,Boolean>hm=new HashMap<>();
        for(int val:arr){
            hm.put(val,true);
        }
        for(int val:arr){
            if(hm.containsKey(val-1)){
                hm.put(val,false);
            }
        }
        int ml=0; 
        int msp=0;
        for(int val:arr){
           int tl=1;
           int tsp=val;
           while(hm.containsKey(tsp+tl)){
               tl++;
           } 
           if(tl>ml){
               ml=tl;
               msp=tsp;
           }
        }
        for(int i=0;i<ml;i++){
            System.out.println(msp+i);
        }
     }  
}

