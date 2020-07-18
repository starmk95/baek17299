import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int len = Integer.parseInt(br.readLine());
        String cmd = br.readLine();
        Stack<Integer> stack = new Stack<>();
        String[] stringArray = cmd.split(" ");
        int[] array = new int[1000001];
        int[] NGF = new int[len];
        for (int i=0;i<len;i++) { // 등장횟수 세고 저장
            array[Integer.parseInt(stringArray[i])] += 1; // 정수의 초기값은 0이다.
        }
        /*
        stack에는 오등큰수가 정해지지 않은 수들의 위치가 들어간다.
        새로운 숫자의 입력이 들어오면 stack의 top부터 등장횟수의 대소비교를 진행한다.
        만약 새로운 숫자의 등장횟수가 크다면, stack에 담긴 숫자(왼쪽의 숫자)의 오큰수가 정해진 것이므로
        이를 stack에서 pop한다.
        새로운 숫자의 등장횟수가 더 작은 경우가 나올때까지 stack의 pop을 계속한다.
        새로운 숫자의 등장횟수가 더 작다면, stack의 top과 새로운 입력 모두 오등큰수가 없는 것이다.
        고로 새로운 숫자를 stack에 push한다.
        입력의 끝에 다다를 때까지 해당과정을 반복한다.
        위 과정에서 stack에 push되는 숫자들은 top으로 갈수록 수가 작아지는 내림차순으로 구성된다.
        (새로운 입력이 들어올 때마다 stack의 top과 등장횟수의 대소비교를 하고 등장횟수가 더 적은 경우에만 push하기 때문)
        (이렇게 stack이 구성되면 입력된 수열의 수가 top의 오등큰수가 되지 못하면 top 밑의 수들에 대해서도 오등큰수가 되지 않는다는 것을 알 수 있음)
         */
        stack.push(0); // 첫 입력은 다른 절차 없이 스택에 push (스택이 비어있기 때문 -> 찾을 오등큰수가 없음)

        for (int i=1;i<len;i++) { // 두번째 입력부터 반복문 처리
            int num = Integer.parseInt(stringArray[i]);
            if (stack.empty()) { // 스택이 비어있다면 다른 절차 없이 무조건 push
                stack.push(i);
                continue;
            }
            // 스택이 비어있지 않을 경우
            while (!stack.empty() && array[Integer.parseInt(stringArray[stack.peek()])] < array[num]) {
                // 입력된 수가 스택의 top의 오등큰수임
                NGF[stack.pop()] = num; // top을 pop하고 오등큰수를 할당
            }
            stack.push(i); // 입력된 수도 아직 오등큰수가 할당되지 않은 수임
        }

        while (!stack.empty()) { // 수열의 모든 수를 입력 받은 후에도 stack에 남이있는 수(오등큰수가 정해지지 않은 수)가 있다면
            NGF[stack.pop()] = -1; // 모두 pop하고 -1 할당해줌
        }

        for (int i=0;i<len;i++) {
            bw.write(NGF[i] + " ");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
