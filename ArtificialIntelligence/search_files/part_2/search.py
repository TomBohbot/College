#search

import state
import frontier

def search(n):
    cost = 0
    s=state.create(n)
    f=frontier.create(s)
    while not frontier.is_empty(f):
        s=frontier.remove(f)
        if state.is_target(s):
            return [s, f[1], cost]
        ns=state.get_next(s)
        for i in ns:
            frontier.insert(f,i)
            cost += 1
    return [0, 0, cost]

def evaluate_search(n):
    """
    Evaluate the search function by running it 100 times and returning key statistics.

    Input: Size of board nxn
    Output: Average max depth, Average number of states
    """
    total_cost = 0
    # Simulate the search algorithm 100 times and get the sum of max depth and num states:
    for i in range(100):
        total_cost += search(n)[2]
    return total_cost/100

# Run simulations on a 2x2 and 3x3 and 4x4 board:
for i in range(2, 4):
    evaluation = evaluate_search(i)
    print(f"Average cost for {i}x{i} sized board: {evaluation}")
    print()





