// Time Complexity: O(n + m) – build adjacency in O(m); DFS visits each node once and each edge twice.
// Space Complexity: O(n + m) – adjacency lists O(m) + arrays discovery/lowest O(n) + recursion stack up to O(n).

// Build adjacency lists from edges; set discovery to -1 and keep global time, discovery[], lowest[], and result list.
// Run Tarjan DFS: on entry set discovery[u] = lowest[u] = time++; for each neighbor ne ≠ parent, recurse if unvisited.
// After returning, if lowest[ne] > discovery[u], record (u, ne) as a bridge; always set lowest[u] = min(lowest[u], lowest[ne]).

class Solution {
    int[] discovery;
    int[] lowest;
    HashMap<Integer, List<Integer>> map;
    int time;
    List<List<Integer>> result;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.discovery = new int[n];
        this.lowest = new int[n];
        this.map = new HashMap<>();
        this.time = 0;
        this.result = new ArrayList<>();

        Arrays.fill(discovery, -1);

        for(int i=0; i<n; i++){
            map.put(i, new ArrayList<>());
        }

        for(List<Integer> connection : connections){
            int u = connection.get(0);
            int v = connection.get(1);

            map.get(u).add(v);
            map.get(v).add(u);
        }

        helper(0, -1);
        return result;
    }

    private void helper(int u, int v){

        if(discovery[u] != -1) return;

        discovery[u] = time;
        lowest[u] = time;
        time++;

        for(int ne : map.get(u)){
            if(ne == v) continue;

            helper(ne, u);

            if(lowest[ne] > discovery[u]){
                result.add(Arrays.asList(ne, u));
            }

            lowest[u] = Math.min(lowest[ne], lowest[u]);
        }
    }
}
