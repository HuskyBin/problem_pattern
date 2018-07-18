很容易能够想到这样一个简单的式子来凑出1/n：




pi=1/i×ii+1×i+1i+2×⋯×n−1n=1n

class RandomSelector:
  def __init__(self, rand=None):
    self._selectedItem = None
    self._count = 0
    self._rand = rand
    if self._rand is None:
      self._rand = Random()

  def SelectedItem(self):
    return self._selectedItem

  def Count(self):
    return self._count

  def AddItem(self, item):
    if self._rand.randint(0, self._count) == 0:
      self._selectedItem = item
    self._count += 1
